package com.simian.service;

import com.simian.entity.DnaEntity;
import com.simian.entity.StatisticEntity;
import com.simian.repository.DnaRepository;
import com.simian.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SimianService {

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private DnaRepository dnaRepository;

    @Transactional
    public Boolean isSimian(List<String> dna){
        dna.forEach(it -> createDna(it));
        if(checkDnaHorizontally(dna)){
            updateStatistics(true);
           return true;
        }else if(checkDiagonallyIfDnaBelongsToASimian(dna)){
            updateStatistics(true);
            return true;
        }else if(checkDiagonallyInverseIfDnaBelongsToASimian(dna)){
            updateStatistics(true);
            return true;
        }else{
            updateStatistics(false);
            return false;
        }
    }

    private boolean checkDnaHorizontally(List<String> dna){
        char sequenceCharIndex = dna.get(0).charAt(0);
        int indexToWordSequence = 1;
        boolean isSimian = false;
        for(int i = 0; i < dna.size(); i++) {
            String dnaSequence = dna.get(i);
            for (int j = 1; j < dnaSequence.length(); j++) {
                if (sequenceCharIndex == dnaSequence.charAt(j)) {
                    indexToWordSequence++;
                } else {
                    sequenceCharIndex = dnaSequence.charAt(j);
                }

                if (indexToWordSequence == 4) {
                    isSimian = true;
                    break;
                }
                System.out.println(dnaSequence.charAt(j));
            }
        }
        return isSimian;
    }

    private boolean checkDiagonallyIfDnaBelongsToASimian(List<String> dna){
        boolean isSimian = false;
        int indexToWordSequence = 1;
        char sequenceCharIndex = dna.get(0).charAt(0);
        DnaEntity dnaEntity = new DnaEntity();
        for(int i = 0; i < dna.size(); i++) {
            if (i < 5 && sequenceCharIndex == dna.get(i + 1).charAt(i + 1)) {
                indexToWordSequence++;
            } else if (i < 5) {
                sequenceCharIndex = dna.get(i + 1).charAt(i + 1);
            }

            if (indexToWordSequence == 4) {
                isSimian = true;
            }
        }
        return isSimian;
    }

    private boolean checkDiagonallyInverseIfDnaBelongsToASimian(List<String> dna){
        boolean isSimian = false;
        int indexToWordSequence = 1;
        char sequenceCharIndex = dna.get(dna.size()-1).charAt(dna.get(dna.size()-1).length() - 1);
        for(int i = dna.size()-1; i > 0; i--) {
            if (sequenceCharIndex == dna.get(i-1).charAt(i-1)) {
                indexToWordSequence++;
            } else if (i < 5) {
                sequenceCharIndex = dna.get(i-1).charAt(i-1);
            }
            if (indexToWordSequence == 4) {
                isSimian = true;
            }
        }
        return isSimian;
    }

    private void createDna(String dna){
        DnaEntity dnaEntity = new DnaEntity();
        dnaEntity.setSequence(dna);
        dnaRepository.save(dnaEntity);
    }

    private void updateStatistics(boolean isSimian){
        StatisticEntity statisticEntity = statisticRepository.findAll().get(0);
        if(isSimian){
            statisticEntity.setMutantDna(statisticEntity.getMutantDna()+1);
        }else{
            statisticEntity.setHumanDna(statisticEntity.getHumanDna()+1);
        }

        statisticRepository.updateStatistic(statisticEntity.getMutantDna(), statisticEntity.getHumanDna(), statisticEntity.getId());

    }
}
