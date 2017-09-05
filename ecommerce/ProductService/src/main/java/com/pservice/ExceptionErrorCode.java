package com.pservice;

/**
 * Created by coviam on 03/08/17.
 */
public enum ExceptionErrorCode {

    VALID_REQUEST(2000, "No error"),
    MISSING_PARAMETER(2001, "Required query parameter is missing"),
    NO_PRODUCT_FOUND(2002, "No product found"),
    WRONG_PRODUCTID(2003, "ProductTable id is not present in database.");


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
