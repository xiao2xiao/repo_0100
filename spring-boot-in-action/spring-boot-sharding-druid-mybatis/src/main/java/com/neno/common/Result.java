package com.neno.common;

/**
 * @Author: root
 * @Date: 2019/2/2 17:08
 */
public class Result<T> {
    private int code;
    private T data;
    private boolean success;
    private String errorMsg;


    public Result(int code, T data, boolean success) {
        this.code = code;
        this.data = data;
        this.success = success;
    }

    public Result(int code, String errorMsg, boolean success) {
        this.code = code;
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
