package com.neno.controller;

import com.neno.model.UserInfo;
import com.neno.serivce.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2019/1/31 12:22
 */
@RestController
public class UserInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @RequestMapping(value = "/queryuserinfos", method = RequestMethod.GET)
    public List<UserInfo> getUserInfos() {
        return userInfoService.getUserLists();
    }

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @RequestMapping(value = "/getuserinfobyid", method = RequestMethod.GET)
    public Map<String, Object> getUserInfo(int id) {
        UserInfo userInfo = userInfoService.getUserInfo(id);
        Map<String, Object> map = new HashMap<>();
        if (userInfo != null) {
            map.put("success", true);
            map.put("OK", 0);
            map.put("UserInfo", userInfo);
        } else {
            map.put("success", false);
            map.put("OK", 1);
            map.put("errMsg", "failed");
        }
        return map;
    }

    /**
     * 增加新用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/adduserinfo", method = RequestMethod.POST)
    public Map<String, Object> addUserInfo(UserInfo userInfo) {
        LOGGER.info("name = " + userInfo.getName());
        Map<String, Object> map = new HashMap<>();
        if (userInfoService.addUserInfo(userInfo)) {
            map.put("success", true);
            map.put("OK", 0);
        } else {
            map.put("success", false);
            map.put("OK", 1);
            map.put("errMsg", "failed");
        }
        return map;
    }
}
