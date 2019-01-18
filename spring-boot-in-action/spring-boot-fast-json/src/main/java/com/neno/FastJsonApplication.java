package com.neno;

import com.alibaba.fastjson.JSON;
import com.neno.model.Address;
import com.neno.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author root
 * @date
 */
@SpringBootApplication
public class FastJsonApplication {
    public static void main(String[] args) {

        SpringApplication.run(FastJsonApplication.class, args);

        System.out.println("Hello World");
        User user = new User();
        user.setName("cxhc");
        user.setCreateTime(new Date());
        user.setAge(11);
        user.setId(1);
        List<Address> list = new ArrayList<>();
        Address address = new Address();
        address.setaArea("江干区");
        address.setCity("杭州市");
        address.setDetail("浙江大学");
        address.setProvince("浙江省");
        Address address1 = new Address();
        address1.setaArea("碑林区");
        address1.setCity("西安市");
        address1.setDetail("西安交通大学");
        address1.setProvince("陕西省");
        list.add(address);
        list.add(address1);
        user.setAddrList(list);
        System.out.println(JSON.toJSONString(user));
    }
}
