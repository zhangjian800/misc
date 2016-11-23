<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	Object obj =  request.getAttribute("App");
	App app = null;
	if(obj!=null){
		app = (App)obj;
	}
%>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />    
    <title>应用维护</title>
    <style type="text/css">
      @import "/dolphin/css/page.css";
      @import "/dolphin/css/table.css";
    </style>
    <script type="text/javascript" language="javascript" src="/dolphin/js/jquery-1.6.1.js"></script>
   
	<script type="text/javascript"> 
		 function upload(){
			$("#uploadForm").submit();
			
		 }
		 
		 var submit = function(){
			var appfile = $("#appfile").val();	
		 	if(appfile=="" || appfile.length==0){
		 		alert("请选择上传文件 !");
				return;
		 	}
	
			uploadForm.action ="/dolphin/submitAppFile.do";
			$("#uploadForm").submit();
	 	
		 };
	</script>
  </head>
  <body id="tables">
	<form name="uploadForm" id="uploadForm" action="/dolphin/submitAppFile.do"  method="post" enctype="multipart/form-data">
    <div id="container">
    	  <h1>上传应用:</h1>
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
    	 	  <input type="hidden" name="appID" value="<%=(app!=null?app.getAppID():"")%>" />
	          <tr>
	            <td>应用代码</td>
	            <td><input type="text" name="appcode" value="<%=(app!=null?app.getAppcode():"")%>" readonly = "true"/></td>
	          </tr>
	          <tr>
	            <td>应用描述</td>
	            <td><input type="text" name="appdesc" value="<%=(app!=null?app.getAppdesc():"")%>" readonly = "true" /></td>
	          </tr>
	          <!--
	          <tr>
	            <td>版本</td>
	            <td><input type="text" name="version" value="<%=(app!=null?app.getVersion():"")%>" readonly = "true" /></td>
	          </tr>
	          -->
	          <tr>
	            <td>应用文件</td>
	            <td>
					<input type="file" name="file" />
				</td>
	          </tr>
	          <tr>
	            <td colspan="2" align="center">	 	
				 <input type="button" name="上传" value="上传" onclick="upload()"/>
				</td>
	          </tr>	        
    	   </table>
    </div>
    </form>
</body>
</html>