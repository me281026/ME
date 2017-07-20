package com.itheima.exam.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
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
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CustomerImgAction extends ActionSupport {
	

	@Autowired
	private ICustomerService customerService;
	
	private static final long serialVersionUID = 1L;

	//处理文件上传
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String cname;
	private String cphone;


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
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

	//添加客户
	@Action(value="addCustomer",results={@Result(name="success",type="redirectAction",location="showCustomer"),
	@Result(name="input",location="/error.jsp")})
	public String addCustomer() {
		//文件上传
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
		System.out.println(path);
		System.out.println(cname+" "+cphone);
		System.out.println(fileFileName);
		File destFile = new File(path,fileFileName);
		try {
			FileUtils.copyFile(file, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//创建Customer对象
		Customer c = new Customer();
		c.setCname(cname);
		c.setCphone(cphone);
		String src = ServletActionContext.getRequest().getContextPath()+"/upload/"+fileFileName;
		c.setcImgSrc(src);
		customerService.addCustomer(c);
		return SUCCESS;
	}

}
