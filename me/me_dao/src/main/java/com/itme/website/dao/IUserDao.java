package com.itme.website.dao;


import com.itme.website.domain.Customer;
import com.itme.website.domain.User;

public interface IUserDao {
	
	public User getUser(String username,String password);

	public Customer getCustomer(User u);

}
