package com.simian.service;

import com.simian.entity.DnaEntity;
import com.simian.entity.StatisticEntity;
import com.simian.error.DnaFormatException;
import com.simian.repository.DnaRepository;
import com.simian.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SimianService {

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private DnaRepository dnaRepository;

    @Transactional
    public Boolean isSimian(List<String> dna) throws DnaFormatException {
        validateDnaSequence(dna);
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
        }else if(checkDnaVertically(dna)){
            updateStatistics(true);
            return true;
        }
        else{
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
                    indexToWordSequence = 1;
                }

                if (indexToWordSequence == 4) {
                    isSimian = true;
                    break;
                }
            }
        }
        return isSimian;
    }

    private boolean checkDnaVertically(List<String> dna){
        boolean isSimian = false;
        char sequenceCharIndex = dna.get(0).charAt(0);
        int indexToWordSequence = 1;

        for(int i =0; i < dna.get(0).length(); i++){
            for(int j = 1; j < dna.size(); j++ ){

                if(sequenceCharIndex == dna.get(j).charAt(i)){
                    indexToWordSequence++;
                }else{
                    sequenceCharIndex = dna.get(j).charAt(i);
                    indexToWordSequence = 1;
                }

                if (indexToWordSequence == 4) {
                    isSimian = true;
                    break;
                }
            }
            indexToWordSequence = 1;
        }

        return isSimian;
    }

    private boolean checkDiagonallyIfDnaBelongsToASimian(List<String> dna){
        boolean isSimian = false;
        int indexToWordSequence = 1;
        char sequenceCharIndex = dna.get(0).charAt(0);

        for(int i = 0; i < dna.size(); i++) {
            if (i < 5 && sequenceCharIndex == dna.get(i + 1).charAt(i + 1)) {
                indexToWordSequence++;
            } else if (i < 5) {
                sequenceCharIndex = dna.get(i + 1).charAt(i + 1);
                indexToWordSequence = 1;
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
                indexToWordSequence = 1;
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

    private void validateDnaSequence(List<String> dna) throws DnaFormatException {
        Pattern pattern = Pattern.compile("[^ATCG]");
        for(String d : dna){
            Matcher matcher = pattern.matcher(d);
            if (matcher.find()) {
                throw new DnaFormatException("Favor utilizar apenas as letras A, T, C, G");
            }
        }
    }
}
