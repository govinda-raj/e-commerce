package com.poc.gen;

/**
 * Created by coviam on 03/08/17.
 */
public class GenericException extends Exception {

    private int errorCode;
    private String errorMsg;

    public GenericException(ExceptionErrorCode code) {
        this.errorMsg = code.getMsg();
        this.errorCode = code.getId();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}