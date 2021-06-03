package com.simian.repository;

import com.simian.model.DnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<DnaEntity, Long> {
    DnaEntity findBySequence(String dna);
}
