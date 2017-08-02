	
	//登录
	function login() {
		//点击进行登录操作
		var username = $("#username").val();
		var password = $("#password").val();
		//向服务器发送请求
		//location.href="/me/user/login?username="+username&+"password="+password;
		$.post("/user_login",{"username":username,"password":password},function(data) {
			/*var jr = eval(data);
			//var jr = data;
			alert(jr);
			alert(jr.status);
			//登录成功
			if(jr.status == 1) {
				alert("恭喜你,登录成功!")
				location.href="http://localhost:8080/person/index.html";
			}*/
		});
		
	};
	
	//自定义函数
	//自定义jquery函数
	$.fn.extend({
		serializeJson : function() {
			//创建一个json对象
			var json = {};
			var msg = this.serializeArray();
			$(msg).each(function() {
				if(json[this.name]) {
					//如果为true
					if(!json[this.name].push) {
						//不是数组的话,变为数组
						json[this.name] = [json[this.name]];
					}
					json[this.name].push(this.value || ''); //放入数组
				}else {
					json[this.name] = this.value || '';
				}
			});
			return json;
		} 
	}); 
