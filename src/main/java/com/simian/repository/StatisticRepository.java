package com.simian.repository;

import com.simian.model.StatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<StatisticEntity, Long> {
    @Modifying
    @Query(value = "UPDATE StatisticEntity set mutantDna = :mutantDna, humanDna= :humanDna where id = :id")
    void updateStatistic(@Param("mutantDna") Long mutantDna, @Param("humanDna") Long humanDna, @Param("id") Long id);
}
