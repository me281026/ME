package com.itme.website.service.impl;

import java.util.List;

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
	@Qualifier("userDAO")
	private IUserDao userDAO;
	

	/**
	 * 通过用户名和账号获取用户
	 */
	@Override
	public User getUser(String username, String password) {
		//获取用户
		List<User> users = userDAO.getUser(username, password);
		if( users != null && users.size() == 1 ) {
			return users.get(0);
		}
		return null;
	}

	/**
	 * 获取到用户对应的客户账号
	 */
	@Override
	public Customer getCustomer(User u) {
		Customer customer = userDAO.getCustomer(u);
		if(customer != null) {
			return customer;
		}else {
			return null;
		}
	}

}
