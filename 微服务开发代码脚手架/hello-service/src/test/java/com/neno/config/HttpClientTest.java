package com.neno.config;

import com.neno.HelloServiceApplicationTests;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @Author: root
 * @Date: 2019/1/20 15:47
 */
public class HttpClientTest extends HelloServiceApplicationTests {

    @Autowired
    private HttpClient httpClient;

    @Test
    public void test1(){
        try {
            System.out.println(EntityUtils.toString(httpClient.execute(new HttpGet("http://www.baidu.com")).getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
