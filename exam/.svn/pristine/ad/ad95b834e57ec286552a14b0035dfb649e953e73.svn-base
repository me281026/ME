package com.itheima.exam.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="t_customer",catalog="sshexam")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String cname;
	private String cphone;
	//账户图片路径
	private String cImgSrc;
	
	//订单
	@OneToMany(targetEntity=Order.class,mappedBy="c")
	@Cascade(CascadeType.ALL)
	private Set<Order> o = new HashSet<Order>();

	
	
	public String getcImgSrc() {
		return cImgSrc;
	}

	public void setcImgSrc(String cImgSrc) {
		this.cImgSrc = cImgSrc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public Set<Order> getO() {
		return o;
	}

	public void setO(Set<Order> o) {
		this.o = o;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", cname=" + cname + ", cphone=" + cphone + ", o=" + o + "]";
	}
	
	
	
}
