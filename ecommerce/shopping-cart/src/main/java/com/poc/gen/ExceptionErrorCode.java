package com.poc.gen;

/**
 * Created by coviam on 03/08/17.
 */
public enum ExceptionErrorCode {

    INVALID_REQUEST(0, "The request is invalid"),
    MISSING_PARAMETER(1, "Required query parameter is missing"),
    MISSING_HEADER(2, "Required header is missing");

    private final int id;
    private final String msg;

    ExceptionErrorCode(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return this.id;
    }

    public String getMsg() {
        return this.msg;
    }
}
