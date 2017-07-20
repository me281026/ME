<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function show(s){
		var x;
		var obj=["我","很","生","气"];
		alert(obj[0]);
		var html= "";
		for (var i = 0; i < 4; i++) {
			x = obj[i]
			alert(x);
			html+="<tr><td><a herf=\"#\" onclick=\"show2('"+x+"')\">"+x+"</a></td></tr>";
			alert(html);
		}
		$("#tab").html(html);
		alert(html);
	}
	
	function show2(x){
		alert(x);
	}

</script>
</head>
<body>

<a  href="#" onclick="show('继续')">删除</a>

<table id="tab">
	
</table>
</body>
</html>