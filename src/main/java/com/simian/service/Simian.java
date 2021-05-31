package com.simian.simian.service;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Simian {

    public Boolean isSimian(List<String> dna){
        //"CTAGAA", "CATAGC", "TAGTGT", "AGAATG", "CCACTA", "TCACTG"
        boolean isSimian = false;
        int indexToWordSequence = 1;
        char sequenceCharIndex = dna.get(0).charAt(0);

        checkDiagonallyIfDnaBelongsToASimian(dna);

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
        }
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
        //"CTAGAA", "CAGAGC", "TAGGGT", "AGAAGG", "CCACTG", "TCACTG"
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

        /*if(i < 5 && sequenceCharIndex == dna.get(i).charAt(i+1)){
            indexToWordSequence++;
        }else if(i < 5){
            sequenceCharIndex = dna.get(i).charAt(i+1);
        }

        if(indexToWordSequence == 4) {
            isSimian = true;
            break;
        }*/
    }
}
