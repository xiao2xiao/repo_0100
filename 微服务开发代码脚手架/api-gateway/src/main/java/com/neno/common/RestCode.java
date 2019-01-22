package com.neno.common;

/**
 * @Author: root
 * @Date: 2019/1/20 16:25
 */
public enum  RestCode {
    /**
     * ok
     */
    OK(0, "OK"),
    /**
     * 评论服务异常
     */
    UNKNOWN_ERROR(1, "评论服务异常"),
    /**
     * 页码不合法
     */
    WRONG_PAGE_NUM(10100, "页码不合法"),
    /**
     * 用户未找到
     */
    USER_NOT_FOUND(10101, "用户未找到"),
    /**
     * 参数不合法
     */
    ILLEGAL_PARAMS(10102, "参数不合法");


    public final int code;
    public final String msg;

    RestCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
