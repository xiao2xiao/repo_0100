package com.neno.model;

import java.io.Serializable;

/**
 * @Author: root
 * @Date: 2019/3/26 23:30
 */
public class Name implements Serializable {
    /**
     * 定义first成员变量
     */
    private String first;
    /**
     * 定义last成员变量
     */
    private String last;

    public Name() {
    }

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Name name = (Name) o;

        if (first != null ? !first.equals(name.first) : name.first != null) {
            return false;
        }
        return last != null ? last.equals(name.last) : name.last == null;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Name{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
