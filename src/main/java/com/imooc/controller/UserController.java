package com.imooc.controller;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

   @RequestMapping("/getuser")
    public User getuser(){
        User user=new User();
        user.setName("solu");
        user.setAge(100);
        user.setBirthday(new Date());
        user.setPassword("xcdscsd");
        return  user;
    }

    @RequestMapping("/getUserJsons")
    public IMoocJSONResult getUserJsons(){
        User user=new User();
        user.setName("imooc66");
        user.setAge(100);
        user.setBirthday(new Date());
        user.setPassword("xcdscsd");
        user.setDesc("play");
        return  IMoocJSONResult.ok(user);
    }
}
