package com.shq.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shq.dao.Tb_userMapper;
import com.shq.pojo.Tb_user;
import com.shq.service.IUserService;

/** 
 * @author  作者：Shengke
 * @date 创建时间：2015年11月26日 下午1:54:44 
 * @version 1.0 
 */
@Service("userService")
public class UserService implements IUserService {

	
	@Resource
	private Tb_userMapper userDao;
	
	public Tb_user getUserById(int userId) {
		// TODO Auto-generated method stub
		
		
		return this.userDao.selectByPrimaryKey(userId);
	}

}
