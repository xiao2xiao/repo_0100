package com.neno.service;

import org.springframework.stereotype.Service;

/**
 * @Author: root
 * @Date: 2019/1/18 14:31
 */
@Service
public class HelloService {
    /**
     * 处理逻辑
     */
    public String handleStr(String text_area_old) {
        String[] str = text_area_old.split("<br>");
        StringBuffer ss = new StringBuffer();
        for (int i = 0; i < str.length - 1; i++) {
            ss.append(str[i]).append(" ");
        }
        ss.append(str[str.length - 1]);
        return new String(ss);
    }
}
