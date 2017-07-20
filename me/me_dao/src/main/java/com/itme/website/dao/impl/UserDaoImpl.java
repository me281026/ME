package com.itme.website.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.itme.website.dao.IUserDao;
import com.itme.website.domain.Customer;
import com.itme.website.domain.User;

@Repository("userDAO")
public class UserDaoImpl extends HibernateDaoSupport implements IUserDao {
	
	@Resource(name="sessionFactory")
	public void setNewSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("all")
	@Override
	public List<User> getUser(String username, String password) {
		return (List<User>) this.getHibernateTemplate().find("from User u where u.username = ? and u.password = ?", new String[]{username,password});
	}

	@Override
	public Customer getCustomer(User u) {
		return (Customer) this.getHibernateTemplate().find("from Customer c where c.user = ?",u).iterator().next();
	}

}
