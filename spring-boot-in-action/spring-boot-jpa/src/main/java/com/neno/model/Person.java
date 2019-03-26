package com.neno.model;

import javax.persistence.*;

/**
 * @Author: root
 * @Date: 2019/3/26 23:29
 */

@Entity
@Table(name = "person_inf")
public class Person {
    /**
     * 以Name组件作为标识属性
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "first",
                    column = @Column(name = "person_firstname")),
            @AttributeOverride(name = "last",
                    column = @Column(name = "person_lastname"))
    })
    private Name name;
    private int age;

    public Person() {
    }

    public Person(Name name, int age) {
        this.name = name;
        this.age = age;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
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
        return "Person{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}
