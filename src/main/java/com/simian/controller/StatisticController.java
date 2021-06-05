package com.simian.controller;

import com.simian.model.Statistic;
import com.simian.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping
    @ResponseBody
    public Statistic stats(){
        return statisticService.getStatistics();
    }
}
