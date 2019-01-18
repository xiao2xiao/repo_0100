package com.neno.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: root
 * @Date: 2019/1/18 14:31
 */
@Service
public class HelloService {
    private Logger logger = LoggerFactory.getLogger(HelloService.class);

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
        String string = new String(ss);
        logger.info("+++++++++++" + handleSpace(string));
        return handleSpace(string);
    }

    /**
     * 处理连续相同的空格
     *
     * @param original
     * @return sb
     */

    public String handleSpace(String original) {
        StringBuilder sb = new StringBuilder();
        /**
         * 标记是否是第一个空格
         */
        boolean isFirstSpace = false;

//        original = original.trim();//如果考虑开头和结尾有空格的情形

        char c;
        for (int i = 0; i < original.length(); i++) {
            c = original.charAt(i);
            /**
             * 遇到空格字符时,先判断是不是第一个空格字符
             */
            if (c == ' ' || c == '\t') {
                if (!isFirstSpace) {
                    sb.append(c);
                    isFirstSpace = true;
                }
            } else {//遇到非空格字符时
                sb.append(c);
                isFirstSpace = false;
            }
        }
        return sb.toString();
    }

}
