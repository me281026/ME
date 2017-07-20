package com.itheima.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.exam.dao.ICustomerDAO;
import com.itheima.exam.domain.Customer;
import com.itheima.exam.service.ICustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDAO customerDAO;
	
	@Override
	public List<Customer> showCustomer() {
		return customerDAO.showCustomer();
		
	}

	@Override
	public void addCustomer(Customer customer) {
		customerDAO.addCustomer(customer);
	}

	@Override
	public void deleteCustomer(int id) {
		Customer customer = customerDAO.deleteCustomerById(id);
		if (customer != null) {
			customerDAO.deleteCustomer(customer);
		}
	}

}
