<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>
<%@ page import="com.dolphin.common.utils.lang.DateUtils" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	Object obj =  request.getAttribute("Rule");
	Rule rule = null;
	if(obj!=null){
		rule = (Rule)obj;
	}
		 obj =  request.getAttribute("ruletype");
	String ruletype = (String)obj;
	
	List versionList = (List)request.getAttribute("VersionList");
	List appList = (List)request.getAttribute("AppList");

%>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />    
    <title>规则维护</title>
    <style type="text/css">
      @import "/dolphin/css/page.css";
      @import "/dolphin/css/table.css";
    </style>
    <script type="text/javascript" language="javascript" src="/dolphin/js/jquery-1.6.1.js"></script>
   
	<script type="text/javascript"> 
		 var submit = function(){
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
	<form name="appForm" id="appForm" action="/dolphin/saveRule.do" method="POST">
    <div id="container">
    	  <h1>规则维护:</h1>
    	  <input type="hidden" name="ruletype" value="<%=(ruletype)%>">

    	  
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
    	 	  <input type="hidden" name="ruleID" value="<%=(rule!=null?rule.getRuleID():"")%>" />
	          <tr>
	            <td>规则代码</td>
	            <td><input type="text" name="rulecode" value="<%=(rule!=null?rule.getRulecode():"")%>" /></td>
	          </tr>
	          <tr style="display: none">
	            <td>省份</td>
	            <td>
	            	<select name="province" id="province" value='<%=(rule!=null?rule.getProvince():"")%>' onchange="changeCity()" >
						<option value='DEFAULT' SELECTED>系统默认</option>
	            	</select>	            	
	            </td>
	          </tr>	          
	          
	          <tr style="display:none">
	            <td>城市</td>
	            <td>
	            	<select name="city" id="city" value="<%=(rule!=null?rule.getCity():"")%>">
						<option value='DEFAULT' SELECTED>系统默认</option>
	            	</select>	            	
	            </td>
	          </tr>	 
	          <tr style="display:none">
	            <td>机型</td>
	            <td>
	            	<select name="productcode">
						<option value='DEFAULT' SELECTED>系统默认</option>
	            	</select>	            	
	            </td>
	          </tr>
  	          <tr>
	            <td>版本</td>
	            <td>
	            	<select name="version" value="<%=(rule!=null?rule.getVersion():"")%>">
		        	<% if(versionList!=null) {
		        			for(int i=0; i < versionList.size(); i++) {
								HashMap map = (HashMap)versionList.get(i);
								String version = rule!=null?rule.getVersion():"";
								boolean isSelectet = version.equals((String)map.get("versioncode"));								
		        	%>
		        				<option value='<%=map.get("versioncode")%>' <%=(isSelectet?"SELECTED":"")%>><%=map.get("versioncode")%></option>
		        	<% 		
		           			} 
		        	   }
		        	%>
	            	</select>
	            </td>
	          </tr>	  
	          <tr style="display:none">
	            <td>客户等级</td>
	            <td>
					<select name="pid" value="<%=(rule!=null?rule.getPid():"")%>">	  
						<option value='M1' SELECTED>M1</option>
						<option value='M2'>M2</option>
						<option value='M3'>M3</option>
						<option value='M4'>M4</option>
						<option value='M5'>M5</option>
						<option value='M6'>M6</option>
						<option value='M7'>M7</option>
						<option value='M8'>M8</option>
						<option value='M9'>M9</option>
					</select>	
	            </td>
	          </tr>
    		  <tr>
	            <td>下次上访时间间隔(分钟)</td>
	            <td><input type="text" name="interval" value="<%=(rule!=null?rule.getInterval():"")%>" />
				</td>
	          </tr>	          
	          <tr>
	            <td>距离第一次上访天数</td>
	            <td><input type="text" name="gapdays" value="<%=(rule!=null?rule.getGapdays():"")%>" /></td>
	          </tr>
     		  <tr>
	            <td>应用</td>
	            <td>
	            	<select type="select" name="appID" value="<%=(rule!=null?rule.getAppID():"")%>">
					        	<% if(appList!=null) {
					        			for(int i=0; i < appList.size(); i++) {
        									App map = (App)appList.get(i);
					        	%>
					        				<option value='<%=map.getAppID()%>'><%=map.getAppID()%>_<%=map.getAppcode()%></option>
					        	<% 		
					           			} 
					        	   }
					        	%>
	            	</select>
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