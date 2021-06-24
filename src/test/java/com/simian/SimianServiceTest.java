package com.simian;

import com.simian.error.DnaFormatException;
import com.simian.service.SimianService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SimianServiceTest {

    @Autowired
    private SimianService simianService;

    @Test
    public void checkDnaHorizontallyThenReturnTrue() throws DnaFormatException {
        List<String> dna = Arrays.asList("CTAGAA", "CAGAGC", "TGGGGT", "AGAGGG", "CCACGG", "TCACTG");
        Assertions.assertTrue(simianService.isSimian(dna));
    }

    @Test
    public void checkDnaHorizontallyThenReturnFalse() throws DnaFormatException {
        List<String> dna = Arrays.asList("CTAGAA", "CAGAGC", "TGGAGT", "AGAGTG", "CCACTG", "TCACTG");
        Assertions.assertFalse(simianService.isSimian(dna));
    }


    @Test
    public void checkDnaVerticallyThenReturnTrue() throws DnaFormatException {
        List<String> dna = Arrays.asList("CGAGAA", "TGGGGC", "CTGGGT", "CTAGGG", "TCACGG", "GCACTG");
        Assertions.assertTrue(simianService.isSimian(dna));
    }

    @Test
    public void checkDnaVerticallyThenReturnFalse() throws DnaFormatException {
        List<String> dna = Arrays.asList("CGAGAA", "TGGTGC", "CTCAGT", "CTAGTG", "TCACTG", "GCACTG");
        Assertions.assertFalse(simianService.isSimian(dna));
    }

    @Test
    public void checkDiagonallyIfDnaBelongsToASimianThenReturnTrue() throws DnaFormatException {
        List<String> dna = Arrays.asList("CAAGAA", "CCAGGC", "TACAGT", "AGACGG", "CCACGG", "TCACTG");
        Assertions.assertTrue(simianService.isSimian(dna));
    }

    @Test
    public void checkDiagonallyIfDnaBelongsToASimianThenReturnFalse() throws DnaFormatException {
        List<String> dna = Arrays.asList("CAAGAT", "CGACTC", "TAGAGT", "AGACTG", "CCACTG", "TCACTG");
        Assertions.assertFalse(simianService.isSimian(dna));
    }


    @Test
    public void checkDiagonallyInverseIfDnaBelongsToASimianThenReturnTrue() throws DnaFormatException {
        List<String> dna = Arrays.asList("CAAGAA", "CAAGGC", "TAGAGT", "AGAGGG", "CCACGG", "TCACTG");
        Assertions.assertTrue(simianService.isSimian(dna));
    }

    @Test
    public void checkDiagonallyInverseIfDnaBelongsToASimianThenReturnFalse() throws DnaFormatException {
        List<String> dna = Arrays.asList("CAAGAA", "CAAGGC", "TAAAGT", "AGACGG", "CCACTG", "TCACTG");
        Assertions.assertTrue(simianService.isSimian(dna));
    }


    @Test
    public void validateDnaSequence() throws DnaFormatException {
        List<String> dna = Arrays.asList("CTAGAA", "CAGAGC", "GAAGGT", "AGAAGG", "CCACAG", "TCACTA");
        Assertions.assertTrue(simianService.isSimian(dna));
    }

    @Test
    public void validateDnaSequenceAndThrowsException() throws DnaFormatException {
        List<String> dna = Arrays.asList("CTAGAA", "CAGAGC", "XAAGGT", "AGAAGG", "CCACAG", "TCACTA");
        Assertions.assertThrows(DnaFormatException.class, () ->
                simianService.isSimian(dna)
        );
    }

}
