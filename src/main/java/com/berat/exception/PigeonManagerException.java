package com.berat.exception;

import lombok.Getter;

@Getter
public class PigeonManagerException extends RuntimeException{
    private final ErrorType errorType;

    public PigeonManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public PigeonManagerException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
