package com.simian.service;

import com.simian.model.StatisticEntity;
import com.simian.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {
    @Autowired
    StatisticRepository statisticRepository;

    private StatisticEntity getStatistics(){

    }
}
