package com.itheima.exam.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="t_order",catalog="sshexam")
public class Order {
	
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String orderNum;
	private String address;
	@Column(precision=25,scale=2)
	private BigDecimal price;
	
	//客户
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="c_id")
	private Customer c;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "Order [orderNum=" + orderNum + ", address=" + address + ", price=" + price + ", c=" + c + "]";
	}
	
	
	

}
