package com.imooc.controller;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

     @Autowired
    private Resource resource;

     @RequestMapping("/hello")
    public  Object hello(){
            return  "springboot";
    }


     @RequestMapping("/resource")
    public IMoocJSONResult  resource(){

         Resource bean=new Resource();
         BeanUtils.copyProperties(resource,bean);

         return  IMoocJSONResult.ok(resource);
     }
}
