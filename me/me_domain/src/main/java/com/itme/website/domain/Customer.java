package com.itme.website.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_customer",catalog="sshperson")
public class Customer {
	
	@Id
	@GenericGenerator(name="cuuid",strategy="uuid")
	@GeneratedValue(generator="cuuid")
	private String cid;
	private String canme;
	private String cpassword;
	
	@Column(precision=30,scale=2)
	private BigDecimal money;
	private String cimgSrc;
	
	//持有用户对象
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="c_uid")
	private User user;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCanme() {
		return canme;
	}
	public void setCanme(String canme) {
		this.canme = canme;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getCimgSrc() {
		return cimgSrc;
	}
	public void setCimgSrc(String cimgSrc) {
		this.cimgSrc = cimgSrc;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", canme=" + canme + ", cpassword=" + cpassword + ", money=" + money
				+ ", cimgSrc=" + cimgSrc + "]";
	}
	
	
	
	

}
