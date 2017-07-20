package com.itheima.exam.dao;

import java.util.List;

import com.itheima.exam.domain.Customer;
import com.itheima.exam.domain.Order;

public interface IOrderDAO {

	List<Order> getOrders(Customer c);

	List<Order> findOrderByPage(Customer c, int pageNum, int pageCount);

	int getCount(Customer c);

	void delOrder(Order o);

	Order findOrderById(String string);

}
