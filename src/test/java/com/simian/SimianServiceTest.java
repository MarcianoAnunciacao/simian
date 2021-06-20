package com.simian;

import com.simian.service.SimianService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

//TODO: Fix tests
@SpringBootTest
public class SimianServiceTest {

    @Autowired
    private SimianService simianService;

    @Test
    public void checkIfIsSimianHorizontally(){
        List<String> dna = Arrays.asList("CTAGAA", "CAGAGC", "TGGGGT", "AGAGGG", "CCACGG", "TCACTG");
        Assertions.assertTrue(simianService.isSimian(dna));
    }

    @Test
    public void checkIfIsSimianDiagonally(){
        List<String> dna = Arrays.asList("CAAGAA", "CAAAGC", "TAGAGT", "AGAGGG", "CCACGG", "TCACTG");
        Assertions.assertTrue(simianService.isSimian(dna));
    }

    @Test
    public void checkIfIsSimianDiagonallyInverse(){
        List<String> dna = Arrays.asList("CTAGAA", "CAGAGC", "TAAGGT", "AGAAGG", "CCACAG", "TCACTA");
        Assertions.assertTrue(simianService.isSimian(dna));
    }
}
