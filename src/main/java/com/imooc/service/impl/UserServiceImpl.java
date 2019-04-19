package com.imooc.service.impl;

import com.imooc.mapper.SysUserMapper;
import com.imooc.pojo.SysUser;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserMapper userMapper;

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(SysUser user) throws Exception {
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		    userMapper.insert(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(SysUser user) {
//		userMapper.updateByPrimaryKeySelective(user);
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(String userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public SysUser queryUserById(String userId) {
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<SysUser> queryUserList(SysUser user) {
		return null;
	}

	@Override
	public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
		return null;
	}

	@Override
	public SysUser queryUserByIdCustom(String userId) {
		return null;
	}

	@Override
	public void saveUserTransactional(SysUser user) {

	}

}
