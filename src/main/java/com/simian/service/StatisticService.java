package com.simian.service;

import com.simian.entity.StatisticEntity;
import com.simian.model.Statistic;
import com.simian.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    @Autowired
    StatisticRepository statisticRepository;

    public Statistic getStatistics(){
        StatisticEntity entity = statisticRepository.findAll().get(0);
        return new Statistic(entity.getMutantDna(), entity.getHumanDna(), entity.getRatio());
    }
}
