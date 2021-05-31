package com.simian.service;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Simian {

    public Boolean isSimian(List<String> dna){
        //"CTAGAA", "CATAGC", "TAGTGT", "AGAATG", "CCACTA", "TCACTG"
        boolean isSimian = false;
        int indexToWordSequence = 1;
        char sequenceCharIndex = dna.get(0).charAt(0);

        isSimian = checkDiagonallyInverseIfDnaBelongsToASimian(dna);

        /*isSimian = checkDiagonallyIfDnaBelongsToASimian(dna);

        for(int i = 0; i < dna.size(); i++){
            String dnaSequence = dna.get(i);
            if(isSimian){
                break;
            }

            isSimian = checkStringDnaHorizontally(dnaSequence);
            if(isSimian){
                break;
            }
            System.out.println(i);
        }*/
        return isSimian;
    }

    private boolean checkStringDnaHorizontally(String dnaSequence){
        char sequenceCharIndex = dnaSequence.charAt(0);
        int indexToWordSequence = 1;
        boolean isSimian = false;
        for(int j = 1; j < dnaSequence.length(); j++){
            if(sequenceCharIndex == dnaSequence.charAt(j)) {
                indexToWordSequence++;
            }else {
                sequenceCharIndex = dnaSequence.charAt(j);
            }

            if(indexToWordSequence == 4) {
                isSimian = true;
                break;
            }
            System.out.println(dnaSequence.charAt(j));
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
        //"CTAGAA", "CAGAGC", "TAGGGT", "AGAGGG", "CCACGG", "TCACTG"
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
