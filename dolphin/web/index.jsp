<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<script type="text/javascript" src="js/jquery-1.6.1.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8,chrome=1" />
<title>欢迎登陆业务管理平台系统!</title>
<link type="text/css" href="css/main.css" rel="stylesheet">
<script type="text/javascript"> 
	 var submit = function(){
		var email = $("#email").val();	 	
	 	if(email=="" || email.length==0){
	 		alert("Please Input email !");
			return;
	 	}
		var password = $("#password").val();	 	
	 	if(password=="" || password.length==0){
	 		alert("Please Input password !");
			return;
	 	}		
		$("#loginForm").submit();
 	
	 };
</script>

<style type="text/css"> 
	.emptyDiv 
	{
		width:150px;
		height:200px;
	}
</style>
</head>

<%
	Object obj = request.getAttribute("failureCode");
	Integer errorCode = new Integer(0);
	if(obj!=null){
		errorCode = (Integer)obj ;
	}
%>
<body bgcolor="#1e5a95" align="Center">
<div>
	<div class="top clearfix">
			<!--
			<h1>
		  		<img height="40" alt="Cisco WebEx Meet" src="images/dolphin.png">
            </h1>
            --> 
		</div>	
	</div>
	<div id="container" style="height:5px;"></div>
</div>
<div class="emptyDiv">
</div>	
<div>
	<div>
     <h1>欢迎登陆业务管理平台系统!</h1>        
	 <form name="loginForm" id="loginForm" action="/dolphin/login.do" method="POST">
	 <div>
		账户: <input type="text" id="email" name="email" />
	 </div>   
	 <div>
		 密码: <input type="password" id="password" name="password" />
	 </div>   
	 <div>
		<%
			if(errorCode.intValue() > 0){
				out.println("<font color='red'>电子邮件地址或密码不正确!</font>");
			}
		%>
	 </div> 
	 </form>
 	 <div>
	 	<input type="button" name="login" value="登陆" onclick="submit()"/>
 	</div> 
   </div>
</div>
</body>
</html>
