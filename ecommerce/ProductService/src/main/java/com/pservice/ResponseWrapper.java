package com.pservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by coviam on 03/08/17.
 */
public class ResponseWrapper<T> {

    private T content;
    private String errorMessage;
    private Integer errorCode;
    private Boolean success;


    public ResponseWrapper(String errorMessage, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public ResponseWrapper(T content, String errorMessage, Integer errorCode, Boolean success) {
        this.content = content;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.success = success;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
