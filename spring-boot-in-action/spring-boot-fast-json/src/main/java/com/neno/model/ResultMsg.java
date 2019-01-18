package com.neno.model;

/**
 * @Author: root
 * @Date: 2019/1/18 19:11
 */
public class ResultMsg {

    private int code;
    private String message;

    public ResultMsg() {
    }

    public ResultMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
