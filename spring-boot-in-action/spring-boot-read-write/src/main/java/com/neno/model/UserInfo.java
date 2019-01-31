package com.neno.model;

import javax.persistence.*;

/**
 * @Author: root
 * @Date: 2019/1/31 12:18
 */
@Table(name = "userinfo")
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

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

}
