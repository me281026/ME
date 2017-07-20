package com.itheima.exam.dao;

import java.util.List;

import com.itheima.exam.domain.Customer;

public interface ICustomerDAO {

	List<Customer> showCustomer();

	void addCustomer(Customer customer);

	Customer deleteCustomerById(int id);

	void deleteCustomer(Customer customer);

	Customer findCustomerById(int id);

}
