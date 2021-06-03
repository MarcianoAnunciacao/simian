package com.simian;

import com.simian.model.StatisticEntity;
import com.simian.repository.StatisticRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StatisticRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    StatisticRepository statisticRepository;

    @Test
    public void whenUpdateStatistic_ThenReturnTheUpdatedOne(){
        StatisticEntity statistic = new StatisticEntity();
        statistic.setHumanDna(15L);
        statistic.setMutantDna(30L);
        StatisticEntity savedStatistic = entityManager.persist(statistic);

        statistic = statisticRepository.findById(savedStatistic.getId()).get();
        statistic.setMutantDna(30L);
        statisticRepository.updateStatistic(statistic.getMutantDna(), statistic.getHumanDna(), statistic.getId());

        StatisticEntity updatedStatistic = statisticRepository.findById(statistic.getId()).get();

        Assert.assertEquals(updatedStatistic.getMutantDna(), statistic.getMutantDna());


    }

}
