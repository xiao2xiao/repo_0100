package com.neno.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: root
 * @Date: 2019/1/20 21:17
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public RestResponse<Object> handler(HttpServletRequest request, Throwable throwable) {
        logger.info(throwable.getMessage(), throwable);
        RestCode restCode = Exception2CodeRepo.getCode(throwable);
        logger.info("handler : code = " + restCode.code + "，msg = " + restCode.msg);
        RestResponse<Object> restResponse = new RestResponse<>(restCode.code, restCode.msg);
        return restResponse;
    }

}
