package com.pservice.exception;

import com.pservice.ExceptionErrorCode;

/**
 * Created by coviam on 03/08/17.
 */
public class GenericException extends Exception {

    private Integer errorCode;
    private String errorMsg;

    public GenericException(ExceptionErrorCode code) {
        this.errorMsg = code.getMsg();
        this.errorCode = code.getId();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
