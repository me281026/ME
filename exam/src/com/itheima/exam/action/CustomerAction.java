package com.itheima.exam.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.exam.domain.Customer;
import com.itheima.exam.service.ICustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ICustomerService customerService;
	
	//删除客户
	@Action(value="delCustomer",results={@Result(name="success",type="redirectAction",location="showCustomer")})
	public String delCustomer() {
		int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));
		customerService.deleteCustomer(id);
		return SUCCESS;
	}
	
	
	//查看客户
	@Action(value="showCustomer",results={@Result(name="success",location="/showCustomer.jsp")})
	public String showCustomer() {
		List<Customer> list = customerService.showCustomer();
		ActionContext.getContext().getValueStack().set("list", list);
		return SUCCESS;
	}

}
