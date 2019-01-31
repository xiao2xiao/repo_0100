package com.neno.controller;

import com.neno.model.UserInfo;
import com.neno.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: root
 * @Date: 2019/1/31 12:22
 */
@RestController
public class UserInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoRepository userInfoRepository;

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @GetMapping("/queryuserinfos")
    public List<UserInfo> getUserInfos() {
        return userInfoRepository.findAll();
    }

    /**
     * 增加新用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/adduserinfo", method = RequestMethod.POST)
    public UserInfo addUserInfo(UserInfo userInfo) {
        LOGGER.info("name = " + userInfo.getName());
        return userInfoRepository.save(userInfo);
    }

    /**
     * 增加新用户后再立即查找该用户信息
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("/userinfo/wr")
    public UserInfo writeAndRead(UserInfo userInfo) {
        LOGGER.info("name = " + userInfo.getName());
        userInfoRepository.saveAndFlush(userInfo);
        return userInfoRepository.findById(userInfo.getId()).get();
    }
}
