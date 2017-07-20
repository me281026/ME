package com.itme.website.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itme.website.dao.IUserDao;
import com.itme.website.domain.Customer;
import com.itme.website.domain.User;
import com.itme.website.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	@Qualifier("userDao")
	private IUserDao userDao;
	

	/**
	 * 通过用户名和账号获取用户
	 */
	@Override
	public User getUser(String username, String password) {
		//获取用户
		User users = userDao.getUser(username, password);
		if( users != null ) {
			return users;
		}
		return null;
	}

	/**
	 * 获取到用户对应的客户账号
	 */
	@Override
	public Customer getCustomer(User u) {
		Customer customer = userDao.getCustomer(u);
		if(customer != null) {
			return customer;
		}
		return null;
	}

}
