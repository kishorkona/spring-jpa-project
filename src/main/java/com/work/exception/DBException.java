package com.work.exception;

import lombok.Data;

@Data
public class DBException extends RuntimeException {

    private String errorCode;
    private String displayMessage;
    
    public DBException(String errorCode, String displayMessage, String message) {
        super(message);
        this.errorCode = errorCode;
        this.displayMessage = displayMessage;
    }
    public DBException(String errorCode, String displayMessage, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.displayMessage = displayMessage;
    }

}
