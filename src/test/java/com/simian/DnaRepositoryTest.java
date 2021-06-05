package com.simian;

import com.simian.entity.DnaEntity;
import com.simian.repository.DnaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DnaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DnaRepository dnaRepository;

    @Test
    public void whenFindBySequence_ThenReturnDna(){
        DnaEntity dna = new DnaEntity();
        dna.setSequence("CTAGAA");
        entityManager.persist(dna);

        DnaEntity dnaFound = dnaRepository.findBySequence("CTAGAA");

        Assert.assertEquals(dnaFound.getSequence(), "CTAGAA");
    }
}
