package com.itme.website.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.itme.website.domain.Customer;
import com.itme.website.domain.UC;
import com.itme.website.domain.User;
import com.itme.website.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class UserAction extends ActionSupport implements ModelDriven<User> {

	/**
	 * Action类
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	private User user;
	private UC uc;

	@Override
	public User getModel() {
		if( user == null ) {
			user = new User();
		}
		return user;
	}
	
	@Action(value="login",results={@Result(name="success",type="redirect",location="/index.html"),
			@Result(name="error",location="/error.html")})
	public String login() {
		System.out.println("进入action.......");
		User u = userService.getUser(user.getUsername(), user.getPassword());
		if( u != null ) {
			//获取用户,存入session
			ServletActionContext.getRequest().getSession().setAttribute("user", u);
			Customer customer = userService.getCustomer(u);
			if ( customer != null ) {
				//获取账户信息,存入session
				ServletActionContext.getRequest().getSession().setAttribute("customer",customer);
				//可以传回数据
				if( uc == null) {
					uc = new UC();
				}
				uc.setStatus(1);
				uc.setUser(u);
				uc.setCustomer(customer);
				String json = JSONObject.toJSONString(uc);
				try {
					ServletActionContext.getResponse().getWriter().write(json);
					System.out.println(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("成功.........");
				return SUCCESS;
			}
			return ERROR;
		} else {
			return ERROR;
		}
		
	}

}
