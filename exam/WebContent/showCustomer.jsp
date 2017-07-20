<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap-theme.min.css">
<script src="${ pageContext.request.contextPath }/js/jquery-1.11.3.js"></script>
<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<style type="text/css">
	.xs{
		padding-left: 120px;
		padding-right: 120px;
	}
</style>
<script type="text/javascript">
		var pageNum = 1; //起始页
		var pageCount = 5; //每页条数
		var totalCount = 0; //总条数
		var totalNum = 0; //总页数
		//删除客户 
		function delCustomer(id) {
			if(confirm("确认删除当前客户")) {
				location.href="${pageContext.request.contextPath}/delCustomer?id="+id;
			}
		}
		
		//获取信息
		var cid;
		function findCustomer(id) {
			$("#orderTable").html("");
			cid = id;
			$.post("${pageContext.request.contextPath}/showOrder",{"id":id,"pageNum":pageNum,"pageCount":pageCount},function(data) {
				var json = eval(data);
				var html = "";
				var order = json.content;
				$(order).each(function() {
					html += "<tr class=\"success\" align=\"center\"><td>"+this.orderNum+"</td><td>"+this.address+"</td><td>"+this.price+"</td><td>"+this.c.cname+"</td><td><input type='checkbox' name='check' value='"+this.orderNum+"' /></td></tr>";
				});
				$("#orderTable").append(html);
				//分页
				pageNum = json.pageNum;
				pageCount = json.pageCount;
				totalCount = json.totalCount;
				totalNum = json.totalNum;
				//判断如果为第一页
				var pagehtml = "<nav><ul class='pagination'>";
				if(pageNum == 1) {
					pagehtml += "<li class='disabled'><a href='javascript:void(0)'>&laquo;</a></li>";
				}else {
					pagehtml += "<li><a href='javascript:void(0)' onclick='prePage()'>&laquo;</a></li>";
				}
				
				//中间页
				for(var i=1;i<=totalNum;i++) {
					if(i==pageNum) {
						pagehtml += "<li class='active'><a href='javascript:void(0)' onclick='select("+i+")'>"+i+"</a></li>";
					}else {
						pagehtml += "<li><a href='javascript:void(0)' onclick='select("+i+")'>"+i+"</a></li>";
					}
				}
				
				//最后一页
				if(pageNum == totalNum) {
					pagehtml += "<li class='disabled'><a href='javascript:void(0)'>&raquo;</a></li>";
				}else {
					pagehtml += "<li><a href='javascript:void(0)' onclick='nextPage()'>&raquo;</a></li>";
				}
				
				pagehtml += "</ul></nav>";
				
				$("#p").html(pagehtml);
				
			},"json");
		}
		
		//向上翻页
		function prePage() {
			pageNum = pageNum-1;
			findCustomer(cid);
		}
		
		function nextPage() {
			pageNum = pageNum+1;
			findCustomer(cid);
		}
		
		function select(id) {
			pageNum = id;
			findCustomer(cid);
		}
		
		//删除订单
		function delOrders() {
			//获取表单数据
			var json = $("#huoqu").serializeJson();
			//发送请求
			if(confirm("删除所选订单")) {
				var check = json.check;
				//如果有多个值,全部转化为string
				var str ="";
				//判断是否为数组
				if(check instanceof Array) {
					//数组则进行遍历,凭借字符串
					for(var i = 0;i<check.length;i++) {
						if(i < check.length-1) {
							str += (check[i] + " ");
						} else {
							str += (check[i]);
						}
					}
					check = str;
				}
				$.post("${pageContext.request.contextPath}/delOrdersByChoice",{"check":check},function(data) {
					var json = "";
					json = data;
					if(json == "success") {
						findCustomer(cid);
					}
				},"json"); 
				//location.href="${pageContext.request.contextPath}/delOrdersByChoice?check="+check;
				
			}
		}
	
		//自定义函数
		$.fn.extend({
			serializeJson:function() {
				var json ={};
				var msg = this.serializeArray();
				$(msg).each(function() {
					if(json[this.name]) {
						if(!json[this.name].push) {
							json[this.name] = [json[this.name]];
						}
						json[this.name].push(this.value || '');
					} else {
						json[this.name]= this.value || '';
					}
					
				});
				return json;
			}
			
		});
		
 
</script>
<title>客户信息</title>
</head>
<body>
	<h3 align="center">客户信息列表</h3>
	<div align="center" class="xs">
		<table class="table table-striped">
			<tr class="danger">
				<td colspan="5"><a href="${pageContext.request.contextPath}/addCustomer.jsp" class="btn btn-primary btn-md active" role="button" >添加客户</a></td>
			</tr>
			<tr align="center" class="info">
				<td>序号</td>
				<td>客户姓名</td>
				<td>客户图像</td>
				<td>客户电话</td>
				<td>操作</td>
			</tr>
			<s:iterator value="list" var="c" status="s">
				<tr align="center">
					<td><s:property value="#s.index+1"/></td>
					<td><s:property value="#c.cname"/></td>
					<td><img src="<s:property value="#c.cImgSrc"/>" class="img-circle"></td>
					<td><s:property value="#c.cphone"/></td>
					<td>
						<button type="button" class="btn btn-danger" onclick="delCustomer(<s:property value="#c.id"/>)">删除客户</button>
						<button type="button" class="btn btn-info" onclick="findCustomer(<s:property value="#c.id"/>)" data-toggle="modal" data-target="#myModal">查看详情</button>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td align="center" colspan="5" id="cm"></td>
			</tr>
		</table>		
	</div>
	
	<!-- 模态框 -->
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    <form class="form" role="form" id="huoqu">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel" align="center">订单详情</h4>
	      </div>
	      <div class="modal-body">

	        <table class="table table-bordered">
				<tr class="active" align="center">
					<td>订单号</td>
					<td>订单地址</td>
					<td>订单金额</td>
					<td>所属客户</td>
					<td>选择</td>
				</tr>
				<tbody id ="orderTable">
					
				</tbody>
			</table>
	      </div>
	      <div class="modal-body" id="p" align="center">
	       	
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary" onclick="delOrders()" id="del">删除</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	      </div>
	      </form>
	    </div>
	  </div>
	</div>
</body>
</html>