package com.simian.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dna_statistic")
public class StatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="mutant_dna")
    private Long mutantDna;

    @Column(name = "human_dna")
    private Long humanDna;

    @Column(name = "ratio")
    private BigDecimal ratio;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getMutantDna() {
        return mutantDna;
    }

    public void setMutantDna(Long mutantDna) {
        this.mutantDna = mutantDna;
    }

    public Long getHumanDna() {
        return humanDna;
    }

    public void setHumanDna(Long humanDna) {
        this.humanDna = humanDna;
    }
}
