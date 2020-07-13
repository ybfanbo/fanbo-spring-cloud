package com.fanbo.user.dao;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDao {

    @RequestMapping("/getUser")
    public String getUser(){
        return "通过RestTemplate方式获取用户信息, Dao 返回 OK！";
    }

}
