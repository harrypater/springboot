package com.imooc.controller;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.SysUser;
import com.imooc.pojo.User;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("redis")
public class RedisController {
	
	@Autowired
	private StringRedisTemplate strRedis;

	@Autowired
	private RedisOperator redis;
	
	@RequestMapping("/test")                                         //存储json对象
	public IMoocJSONResult test() {

		//strRedis.opsForValue().set("imooc-cache", "hello 慕课网~~~~~~");
		SysUser user=new SysUser();
		user.setId("three");
		user.setName("json-sysuser");

		strRedis.opsForValue().set("json-sysuser", JsonUtils.objectToJson(user));
		SysUser pojo=JsonUtils.jsonToPojo(strRedis.opsForValue().get("json-sysuser"),SysUser.class);

		 return  IMoocJSONResult.ok(pojo);
	}

	@RequestMapping("/getJsonList")                                //存储多个json对象
	public IMoocJSONResult getJsonList() {

		User user = new User();


		User u1 = new User();
		u1.setAge(19);
		u1.setName("imooc");
		u1.setPassword("123456");
		u1.setBirthday(new Date());

		User u2 = new User();
		u2.setAge(17);
		u2.setName("hello imooc");
		u2.setPassword("123456");
		u2.setBirthday(new Date());

		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(u1);
		userList.add(u2);

		redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 2000);

		String userListJson = redis.get("json:info:userlist");
		List<User> userListBorn = JsonUtils.jsonToList(userListJson, User.class);

		return IMoocJSONResult.ok(userListBorn);
	}


}