package com.imooc.mapper;

import com.imooc.pojo.SysUser;
import com.imooc.utils.MyMapper;

import java.util.List;

public interface SysUserMapperCustom extends MyMapper<SysUser> {

     List<SysUser> queryUserSimplyInfoById(String id);
}