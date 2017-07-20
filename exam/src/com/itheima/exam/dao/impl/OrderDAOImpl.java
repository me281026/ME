package com.itheima.exam.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.itheima.exam.dao.IOrderDAO;
import com.itheima.exam.domain.Customer;
import com.itheima.exam.domain.Order;

@Repository
public class OrderDAOImpl extends HibernateDaoSupport implements IOrderDAO {
	
	@Autowired
	public void setNewSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders(Customer c) {
		return (List<Order>) this.getHibernateTemplate().find("from Order o where o.c = ?",c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrderByPage(Customer c, int pageNum, int pageCount) {
		DetachedCriteria dc = DetachedCriteria.forClass(Order.class);
		dc.add(Restrictions.eq("c", c));
		return (List<Order>) this.getHibernateTemplate().findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
	}

	@Override
	public int getCount(Customer c) {
		return ((Long)this.getHibernateTemplate().find("select count(*) from Order o where o.c = ?",c).iterator().next()).intValue();
	}

	@Override
	public void delOrder(Order o) {
		this.getHibernateTemplate().delete(o);
	}

	@Override
	public Order findOrderById(String string) {
		return this.getHibernateTemplate().get(Order.class, string);
	}

}
