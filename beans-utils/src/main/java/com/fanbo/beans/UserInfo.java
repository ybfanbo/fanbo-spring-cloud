package com.fanbo.beans;

import java.io.Serializable;

/**
 * @Description: $description
 * @Author: FanBo
 * @Date: 2020/7/21
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 2467122212272659745L;

    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
