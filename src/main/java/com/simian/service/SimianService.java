package com.simian.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SimianService {

    public Boolean isSimian(List<String> dna){
        if(checkDnaHorizontally(dna)){
           return true;
        }else if(checkDiagonallyIfDnaBelongsToASimian(dna)){
            return true;
        }else if(checkDiagonallyInverseIfDnaBelongsToASimian(dna)){
            return true;
        }else{
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
}
