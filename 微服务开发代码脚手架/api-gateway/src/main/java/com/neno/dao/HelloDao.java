package com.neno.dao;

import com.neno.common.RestResponse;
import com.neno.config.GenericRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

/**
 * @Author: root
 * @Date: 2019/1/20 16:36
 */
@Repository
public class HelloDao {

    @Autowired
    private GenericRest rest;

    @Value("${hello.service.name}")
    private String helloServiceName;

//    public String getUsername(long id) {
//        String url = "http://HELLO-SERVICE/getusername?id=" + id;
//        RestResponse<String> restResponse = rest.get(url, new ParameterizedTypeReference<RestResponse<String>>() {
//        }).getBody();
//        return restResponse.getResult();
//    }

    public String getUsername(long id) {
        String url = "http://" + helloServiceName + "/getusername?id=" + id;
        RestResponse<String> restResponse = rest.get(url, new ParameterizedTypeReference<RestResponse<String>>() {
        }).getBody();
        if (restResponse.getCode() == 0) {
            return restResponse.getResult();
        }
        throw  new IllegalStateException("handle error.....");
    }

}
