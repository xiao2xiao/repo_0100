package com.neno.vo;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: root
 * @Date: 2019/3/27 14:25
 */

@Entity
@Immutable
@Subselect(value = "select name,age from v_user")
public class UserVo implements Serializable {
    @Id
    private String name;
    private int age;

    public UserVo() {

    }

    public UserVo(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
