package com.simian;

import com.simian.model.Statistic;
import com.simian.service.StatisticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatisticServiceTest {

    @Autowired
    StatisticService statisticService;

    @Test
    public void shouldGetStatistics(){
        Statistic statistic = statisticService.getStatistics();
        Assertions.assertNotNull(statistic);
    }
}
