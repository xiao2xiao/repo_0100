package com.neno.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * @Author: root
 * @Date: 2019/1/18 17:53
 */
public class User {
    private int id;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    /**
     * 创建时间
     */
    private Date createTime;
    @JSONField
    private String name;
    @JSONField
    private int age;
    @JSONField(name = "list")
    private List<Address> addrList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Address> getAddrList() {
        return addrList;
    }

    public void setAddrList(List<Address> addrList) {
        this.addrList = addrList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
