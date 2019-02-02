package com.neno.serivce;

import com.neno.mapper.UserInfoMapper;
import com.neno.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: root
 * @Date: 2019/2/2 16:34
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> getUserLists() {
        return userInfoMapper.selectUserInfos();
    }

    public UserInfo getUserInfo(int id) {
        UserInfo userInfo = userInfoMapper.queryUserById(id);
        if (userInfo == null) {
            return null;
        }
        return userInfo;
    }

    public boolean addUserInfo(UserInfo userInfo) {
        if (userInfoMapper.insertUserInfo(userInfo) > 0) {
            return true;
        }
        return false;
    }

    public boolean modifyUserInfo(UserInfo userInfo) {
        if (userInfoMapper.updateUserInfo(userInfo) > 0) {
            return true;
        }
        return false;
    }
}
