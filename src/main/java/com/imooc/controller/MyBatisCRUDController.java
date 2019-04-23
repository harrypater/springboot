package com.imooc.controller;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.SysUser;
import com.imooc.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("mybatis")
public class MyBatisCRUDController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private Sid sid;
	
	@RequestMapping("/saveUser")                         // 增加操作
	public IMoocJSONResult saveUser() throws Exception {
		
		String userId =sid.nextShort();

		Random rand =new Random();    // seed
		int i;
		i=rand.nextInt(100);

		SysUser user = new SysUser();
		user.setId(userId);
		user.setName("zeep");
		user.setAge(i);
		userService.saveUser(user);
		
		return IMoocJSONResult.ok("保存成功");
	}


	@RequestMapping("/updateUser")                 // 更新操作
	public IMoocJSONResult updateUser() {

		SysUser user = new SysUser();
		user.setId("2");
		user.setName("toms-new");
		user.setAge(66);

		userService.updateUser(user);

		return IMoocJSONResult.ok("保存成功");
	}
	
	@RequestMapping("/deleteUser")
	public IMoocJSONResult deleteUser(String userId) {
		
		userService.deleteUser(userId);
		
		return IMoocJSONResult.ok("删除成功");
	}
	
	@RequestMapping("/queryUserById")
	public IMoocJSONResult queryUserById(String userId) {
		
		return IMoocJSONResult.ok(userService.queryUserById(userId));
	}
	

	@RequestMapping("/queryUserListPaged")
	public IMoocJSONResult queryUserListPaged(Integer page) {
		
		if (page == null) {
			page = 1;
		}

		int pageSize =2;
		
		SysUser user = new SysUser();
//		user.setNickname("lee");
		
		List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);
		
		return IMoocJSONResult.ok(userList);
	}
	
	@RequestMapping("/queryUserByIdCustom")
	public IMoocJSONResult queryUserByIdCustom(String userId) {
		
		return IMoocJSONResult.ok(userService.queryUserByIdCustom(userId));
	}

}
