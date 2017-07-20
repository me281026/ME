package com.itheima.exam.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.exam.domain.Order;
import com.itheima.exam.domain.PageBean;
import com.itheima.exam.service.IOrderService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class OrderAction extends ActionSupport {

	/**
	 * 
	 */
	@Autowired
	private IOrderService orderService;
	
	private static final long serialVersionUID = 1L;
	
	
	@Action(value="delOrdersByChoice") 
	public void delOrder() {
		String check = ServletActionContext.getRequest().getParameter("check");
		String status = "";
		if(check.trim().contains(" ")) {
			String[] str = check.split(" ");
			//多个值
			for (int i = 0; i < str.length; i++) {
				System.out.println(str[i]);
				orderService.delOrder(str[i]);
			}
			status += "success";
		}else {
			//单个值
			System.out.println(check);
			orderService.delOrder(check);
			status += "success";
		}
		try {
			String json = JSONObject.toJSONString(status);
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Action(value="showOrder")
	public void showOrder() {
		//客户id
		int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));
		//起始页码
		int pageNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		//每页条数
		int pageCount = Integer.parseInt(ServletActionContext.getRequest().getParameter("pageCount"));
		//查看所获取id的客户订单
		/*List<Order> list = orderService.showOrder(id);*/
		
		PageBean<Order> pb = orderService.findOrderByPage(id,pageNum,pageCount);
		
		PropertyFilter pf = new PropertyFilter() {
			
			@Override
			public boolean apply(Object obj, String fieldName, Object arg2) {
				if("cphone".equalsIgnoreCase(fieldName)) {
					return false;
				}
				if("id".equalsIgnoreCase(fieldName)) {
					return false;
				}
				if("o".equalsIgnoreCase(fieldName)) {
					return false;
				}
				return true;
			}
		};
		String json = JSONObject.toJSONString(pb,pf,SerializerFeature.DisableCircularReferenceDetect);
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
}
