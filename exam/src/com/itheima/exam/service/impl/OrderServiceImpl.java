package com.itheima.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.exam.dao.ICustomerDAO;
import com.itheima.exam.dao.IOrderDAO;
import com.itheima.exam.domain.Customer;
import com.itheima.exam.domain.Order;
import com.itheima.exam.domain.PageBean;
import com.itheima.exam.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderDAO orderDAO;
	
	@Autowired
	ICustomerDAO customerDAO;
	
	@Override
	public List<Order> showOrder(int id) {
		//先获取客户
		Customer c = customerDAO.findCustomerById(id);
		if(c != null) {
			return orderDAO.getOrders(c);
		}
		return null;
	}

	@Override
	public PageBean<Order> findOrderByPage(int id, int pageNum, int pageCount) {
		Customer c = customerDAO.findCustomerById(id);
		if(c != null) {
			PageBean<Order> pb = new PageBean<Order>();
			pb.setPageNum(pageNum);
			pb.setPageCount(pageCount);
			int totalCount = orderDAO.getCount(c);
			pb.setTotalCount(totalCount);
			pb.setTotalNum((int) Math.ceil(totalCount*1.0/pageCount));
			List<Order> orders = orderDAO.findOrderByPage(c,pageNum,pageCount);
			pb.setContent(orders);
			return pb;
		}
		return null;
		
	}

	@Override
	public void delOrder(String string) {
		Order o = orderDAO.findOrderById(string);
		if( o != null) {
			orderDAO.delOrder(o);
		}
		
	}

}
