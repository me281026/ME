package com.itheima.exam.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.itheima.exam.dao.ICustomerDAO;
import com.itheima.exam.domain.Customer;

@Repository
public class CustomerDAOImpl extends HibernateDaoSupport implements ICustomerDAO {

	@Autowired
	public void setNewSessionFactory(SessionFactory sesssionFactory) {
		super.setSessionFactory(sesssionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> showCustomer() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	@Override
	public void addCustomer(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	@Override
	public Customer deleteCustomerById(int id) {
		return this.getHibernateTemplate().get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		
		this.getHibernateTemplate().delete(customer);
	}

	@Override
	public Customer findCustomerById(int id) {
		return this.getHibernateTemplate().get(Customer.class,id);
	}

}
