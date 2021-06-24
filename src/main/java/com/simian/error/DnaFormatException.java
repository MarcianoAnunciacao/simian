package com.simian.error;

public class DnaFormatException extends RuntimeException{
    public DnaFormatException(String errorMessage){
        super(errorMessage);
    }
}
