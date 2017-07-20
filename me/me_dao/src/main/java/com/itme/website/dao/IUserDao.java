package com.itme.website.dao;

import java.util.List;

import com.itme.website.domain.Customer;
import com.itme.website.domain.User;

public interface IUserDao {
	
	public List<User> getUser(String username,String password);

	public Customer getCustomer(User u);

}
