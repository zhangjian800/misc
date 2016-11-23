<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>
<%@ page import="com.dolphin.common.utils.lang.DateUtils" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	Access access =  (Access)request.getAttribute("Access");
%>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />    
    <title>查看访问明细</title>
    <style type="text/css">
      @import "/dolphin/css/page.css";
      @import "/dolphin/css/table.css";
    </style>
    <script type="text/javascript" language="javascript" src="/dolphin/js/jquery-1.6.1.js"></script>
	<script type="text/javascript"> 
	</script>
  </head>
  <body id="tables">
	<form name="appForm" id="appForm" action="/dolphin/saveRule.do" method="POST">
    <div id="container">
    	  <h1>明细查看:</h1>  	  
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
	          <tr>
	            <td>IMSI</td>
	            <td><input type="text" name="access" value="<%=(access!=null?access.getImsi():"")%>" readonly/></td>
	          </tr>
	          <tr>
	            <td>省份</td>
	            <td><input type="text" name="access" value="<%=(access!=null?access.getProvince():"")%>" readonly/></td>
	          </tr>	          
	          <tr>
	            <td>城市</td>
	            <td><input type="text" name="access" value="<%=(access!=null?access.getCity():"")%>" readonly/></td>
	          </tr>	 
	          <tr>
	            <td>机型</td>
	            <td><input type="text" name="access" value="<%=(access!=null?access.getProductType():"")%>" readonly size="40"/></td>
	          </tr>
	          
	          <tr>
	            <td>版本</td>
	            <td><input type="text" name="access" value="<%=(access!=null?access.getVersion():"")%>" readonly/></td>
	          </tr>	  
	          
	          <tr>
	            <td>应用ID</td>
	            <td><input type="text" name="access" value="<%=(access!=null?access.getResponesAppID():"")%>" readonly/></td>
	          </tr>	 
        	   <tr>
	            <td>请求</td>
	            <td><textarea name="access" rows="12" cols="60" value=""><%=(access!=null?access.getRequestStr():"")%></textarea></td>
	          </tr>	 			  
			  <tr>
	            <td>结果</td>
	            <td><input type="text" name="access" value="<%=(access!=null?access.getResponseStatus():"")%>" readonly/></td>
	          </tr>	 
        	   <tr>
	            <td>返回内容</td>
	            <td><textarea name="access" rows="24" cols="100" value=""><%=(access!=null?access.getResponseScriptContent():"")%></textarea></td>
	          </tr>	  
    	   </table>
    </div>
    </form>
</body>
</html>