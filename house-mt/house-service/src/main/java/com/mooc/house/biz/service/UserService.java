package com.mooc.house.biz.service;

import com.mooc.house.biz.mapper.UserMapper;
import com.mooc.house.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: root
 * @Date: 2019/2/13 23:51
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList() {
        return userMapper.selectUsers();
    }
}
