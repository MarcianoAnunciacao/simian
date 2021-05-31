package com.simian;

import com.simian.service.Simian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SimianTest {

    @Autowired
    private Simian simian;

    @Test
    public void isSimian(){
        List<String> dna = Arrays.asList("CTAGAA", "CAGAGC", "TAGGGT", "AGAGGG", "CCACGG", "TCACTG");
        Assertions.assertTrue(simian.isSimian(dna));
    }
}
