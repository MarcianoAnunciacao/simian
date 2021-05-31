package com.simian.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "statistic")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name="mutant_dna")
    private Long mutantDna;

    @Column(name = "human_dna")
    private Long humanDna;

    @Column(name = "ratio")
    private BigDecimal ratio;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
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
