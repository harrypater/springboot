package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.imooc.mapper.SysUserMapper;
import com.imooc.mapper.SysUserMapperCustom;
import com.imooc.pojo.SysUser;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserMapper userMapper;

	@Autowired
	private SysUserMapperCustom userMapperCustom;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(SysUser user) throws Exception {                     //增加操作
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		    userMapper.insert(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)                      //更新实现
	public void updateUser(SysUser user) {
		userMapper.updateByPrimaryKeySelective(user);                //有值才更新
//		userMapper.updateByPrimaryKey(user);
	}

	@Override  																					//删除实现
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(String userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)                 //id查询
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

																					//分页的实现
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
		// 开始分页
		PageHelper.startPage(page, pageSize);

		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmptyOrWhitespace(user.getName())) {
			criteria.andLike("name", "%" + user.getName() + "%");
		}

		example.orderBy("name").desc();
		List<SysUser> userList = userMapper.selectByExample(example);

		return userList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)                //自定义id查询
	public SysUser queryUserByIdCustom(String userId) {

		List<SysUser> userList = userMapperCustom.queryUserSimplyInfoById(userId);

		if (userList != null && !userList.isEmpty()) {
			return (SysUser)userList.get(0);
		}

		return null;
	}


	@Override                                                                //事物回滚
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserTransactional(SysUser user) {                        //mapper 操作

		userMapper.insert(user);

		int a = 1 / 0;
		user.setName("delete");

		userMapper.updateByPrimaryKeySelective(user);
	}

}
