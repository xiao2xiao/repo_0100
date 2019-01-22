package com.neno.service;

import com.neno.dao.HelloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: root
 * @Date: 2019/1/20 16:42
 */
@Service
public class HelloService {
    @Autowired
    private HelloDao helloDao;

    public String getUsername(long id) {
        return helloDao.getUsername(id);
    }
}
