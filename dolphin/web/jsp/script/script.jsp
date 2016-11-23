<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	Object obj =  request.getAttribute("Script");
	Script script = null;
	if(obj!=null){
		script = (Script)obj;
	}
%>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />    
    <title>DataTables live example</title>
    <style type="text/css">
      @import "/dolphin/css/page.css";
      @import "/dolphin/css/table.css";
    </style>
    <script type="text/javascript" language="javascript" src="/dolphin/js/jquery-1.6.1.js"></script>
   
	<script type="text/javascript"> 
		 var submit = function(){
			var scriptCode = $("#scriptCode").val();	
			alert("scriptCode=="+scriptCode);
		 	if(scriptCode=="" || scriptCode.length==0){
		 		alert("Please Input scriptCode !");
				return;
		 	}
			var scriptDesc = $("#scriptDesc").val();	 	
		 	if(scriptDesc=="" || scriptDesc.length==0){
		 		alert("Please Input scriptDesc !");
				return;
		 	}		
			var scriptcontent = $("#scriptcontent").val();	 	
		 	if(scriptcontent=="" || scriptcontent.length==0){
		 		alert("Please Input scriptcontent !");
				return;
		 	}	
			$("#scriptForm").submit();
	 	
		 };
	</script>
  </head>
  <body id="tables">
	<form name="scriptForm" id="scriptForm" action="/dolphin/saveScript.do" method="POST">
    <div id="container">
    	  <h1>新增脚本:</h1>
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
    	  
    	 		<input type="hidden" name="scriptID" value="<%=(script!=null?script.getScriptID():"")%>" />
	          <tr>
	            <td>脚本代码</td>
	            <td><input type="text" name="scriptCode" value="<%=(script!=null?script.getScriptCode():"")%>" /></td>
	          </tr>
	          <tr>
	            <td>脚本描述</td>
	            <td><input type="text" name="scriptDesc" value="<%=(script!=null?script.getScriptDesc():"")%>" /></td>
	          </tr>
	          <tr>
	            <td>脚本内容</td>
	            <td><textarea rows="24" cols="100" name="scriptcontent" value="" ><%=(script!=null?script.getScriptcontent():"")%></textarea>
				</td>
	          </tr>	  
	          
	          <tr>
	            <td colspan="2" align="center">	 	
				 <input type="button" name="login" value="保存" onclick="submit()"/>
				</td>
	          </tr>	        
	          
    	   </table>
    </div>
    </form>
</body>
</html>