package com.itme.website.service;

import com.itme.website.domain.Customer;
import com.itme.website.domain.User;

public interface IUserService {
	
	public User getUser(String username,String password);

	public Customer getCustomer(User u);

}
