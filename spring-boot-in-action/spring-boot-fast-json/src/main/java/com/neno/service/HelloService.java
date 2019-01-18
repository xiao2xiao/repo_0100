package com.neno.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neno.model.User;
import org.springframework.stereotype.Service;

/**
 * @Author: root
 * @Date: 2019/1/18 17:59
 */
@Service
public class HelloService {
    public User getUserById(int id) {
        String jsonStr = "{\"age\":11,\"createTime\":\"2019-01-18 19:01:28\",\"id\":" + id + ",\"list\":[{\"area\":\"江干区\",\"city\":\"杭州市\",\"detail\":\"浙江大学\",\"province\":\"浙江省\"},{\"area\":\"碑林区\",\"city\":\"西安市\",\"detail\":\"西安交通大学\",\"province\":\"陕西省\"}]}";
        User user = JSONObject.parseObject(jsonStr, User.class);
        return user;
    }

    public boolean addUser(User user) {
        System.out.println(JSON.toJSONString(user));
        return true;
    }
}
