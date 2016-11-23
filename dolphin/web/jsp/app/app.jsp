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
	
	List versionList = (List)request.getAttribute("VersionList");
	
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
		 var submit = function(){
			var appcode = $("#appcode").val();	
		 	if(appcode=="" || appcode.length==0){
		 		alert("Please Input appcode !");
				return;
		 	}
			var appdesc = $("#appdesc").val();	 	
		 	if(appdesc=="" || appdesc.length==0){
		 		alert("Please Input appdesc !");
				return;
		 	}		
			var version = $("#version").val();	 	
		 	if(version=="" || version.length==0){
		 		alert("Please Input version !");
				return;
		 	}	
			$("#appForm").submit();
	 	
		 };
	</script>
  </head>
  <body id="tables">
	<form name="appForm" id="appForm" action="/dolphin/saveApp.do" method="POST">
    <div id="container">
    	  <h1>应用维护:</h1>
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
    	 	  <input type="hidden" name="appID" value="<%=(app!=null?app.getAppID():"")%>" />
	          <tr>
	            <td>应用代码</td>
	            <td><input type="text" name="appcode" value="<%=(app!=null?app.getAppcode():"")%>" /></td>
	          </tr>
	          <tr>
	            <td>应用描述</td>
	            <td><input type="text" name="appdesc" value="<%=(app!=null?app.getAppdesc():"")%>" /></td>
	          </tr>
	          <!--
	          <tr>
	            <td>版本</td>
	            <td>
	            	<select name="version" value="<%=(app!=null?app.getVersion():"")%>">
		        	<% if(versionList!=null) {
		        			for(int i=0; i < versionList.size(); i++) {
								HashMap map = (HashMap)versionList.get(i);
		        	%>
		        				<option value='<%=map.get("versioncode")%>'><%=map.get("versioncode")%></option>
		        	<% 		
		           			} 
		        	   }
		        	%>
	            	</select>
	            </td>
	          </tr>
	          -->	          
	          <tr>
	            <td>应用业务类型</td>
	            <td>
				<select name ="apptype" value="<%=(app!=null?app.getApptype():"")%>" >
					<option value="1" <%=(app!=null && app.getApptype().equals("1"))?"SELECTED":""%>>广告</option>
					<option value="2" <%=(app!=null && app.getApptype().equals("2"))?"SELECTED":""%>>扣费</option>
					<option value="3" <%=(app!=null && app.getApptype().equals("3"))?"SELECTED":""%>>系统</option>
			0</select>
	            </td>
	          </tr0	     
	            	
	          <tr>
	            <td>扣费业务单次价格</td>
	            <td><input type="text" name="charge" value="<%=(app!=null?app.getCharge():"")%>" /></td>
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