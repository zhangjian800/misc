<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>
<%@ page import="com.dolphin.common.utils.lang.DateUtils" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	Object obj =  request.getAttribute("SystemConfig");
	SystemConfig sc = null;
	if(obj!=null){
		sc = (SystemConfig)obj;
	}

%>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />    
    <title>系统参数配置</title>
    <style type="text/css">
      @import "/dolphin/css/page.css";
      @import "/dolphin/css/table.css";
    </style>
    <script type="text/javascript" language="javascript" src="/dolphin/js/jquery-1.6.1.js"></script>
   
	<script type="text/javascript"> 
		 var submit = function(){
			$("#sysForm").submit();
		 };
		 
	</script>
  </head>
  <body id="tables">
	<form name="sysForm" id="sysForm" action="/dolphin/saveSystemRule.do" method="POST">
    <div id="container">
    	  <h1>系统参数维护:</h1>
    	  <input type="hidden" name="confID" value="<%=(sc!=null?sc.getConfID():"")%>">
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
	          <tr>
	            <td>规则代码</td>
	            <td><input type="text" name="confCode" value="<%=(sc!=null?sc.getConfCode():"")%>" /></td>
	          </tr>
	          <tr>
	            <td>每月最大访问次数</td>
	            <td><input type="text" name="maxMonthhits" value="<%=(sc!=null?sc.getMaxMonthhits():"")%>" /></td>
	          </tr>       
	          <tr>
	            <td>每月最大扣费金额</td>
	            <td><input type="text" name="maxMonthCharge" value="<%=(sc!=null?sc.getMaxMonthCharge():"")%>" /></td>
	          </tr>     
	          <tr>
	            <td>系统默认下次上访天数(无规则)</td>
	            <td><input type="text" name="defaultIntevalDays" value="<%=(sc!=null?sc.getDefaultIntevalDays():"")%>" /></td>
	          </tr>  
	          <tr>
	            <td>系统拦截默认下次上访天数</td>
	            <td><input type="text" name="blockIntevalDays" value="<%=(sc!=null?sc.getBlockIntevalDays():"")%>" /></td>
	          </tr>
	          
	          <tr>
	            <td>是否启用系统工作时间拦截--广告</td>
	            <td>
	            	<select type="select" name="isblockworkingtime" value="<%=(sc!=null?sc.getIsblockworkingtime():"")%>">
					    <option value='1' <%=(sc.getIsblockworkingtime()==1)?"SELECTED":""%> >Enable</option>
						<option value='0' <%=(sc.getIsblockworkingtime()==0)?"SELECTED":""%>  >Disable</option>
	            	</select>
				</td>
	          </tr>	          
	          <tr>
	            <td>广告系统工作时间--开始(8:00)</td>
	            <td><input type="text" name="begintime" value="<%=(sc!=null?sc.getBegintime():"")%>" /></td>
	          </tr>  
	          <tr>
	            <td>广告系统工作时间--结束(21:00)</td>
	            <td><input type="text" name="endtime" value="<%=(sc!=null?sc.getEndtime():"")%>" /></td>
	          </tr>
	          <tr>
	            <td>广告商没有数据上访间隔(分钟）</td>
	            <td><input type="text" name="defaultAdsIntevalMins" value="<%=(sc!=null?sc.getDefaultAdsIntevalMins():"")%>" /></td>
	          </tr>	 	          
	          <tr>
	            <td>广告缓冲区大小</td>
	            <td><input type="text" name="maxadsbuffersize" value="<%=(sc!=null?sc.getMaxadsbuffersize():"")%>" /></td>
	          </tr>	  
	          <tr>
	            <td>单次从广告商取得数据最大条数</td>
	            <td><input type="text" name="sizeofpercall" value="<%=(sc!=null?sc.getSizeofpercall():"")%>" /></td>
	          </tr>	  
	          
	          <tr>
	            <td>每日最高广告数据(万条)</td>
	            <td><input type="text" name="maxadsdata" value="<%=(sc!=null?sc.getMaxadsdata():"")%>" /></td>
	          </tr>	  	          
	          	  
	          <tr>
	            <td>是否启用系统工作时间拦截--付费</td>
	            <td>
	            	<select type="select" name="filternonworkingtime4charge" value="<%=(sc!=null?sc.getFilternonworkingtime4charge():"")%>">
					    <option value='1' <%=(sc.getFilternonworkingtime4charge()==1)?"SELECTED":""%> >Enable</option>
						<option value='0' <%=(sc.getFilternonworkingtime4charge()==0)?"SELECTED":""%>  >Disable</option>
	            	</select>
				</td>
	          </tr>	          
	          <tr>
	            <td>付费系统工作时间--开始(8:00)</td>
	            <td><input type="text" name="chargebegintime" value="<%=(sc!=null?sc.getChargebegintime():"")%>" /></td>
	          </tr>  
	          <tr>
	            <td>付费系统工作时间--结束(21:00)</td>
	            <td><input type="text" name="chargeendtime" value="<%=(sc!=null?sc.getChargeendtime():"")%>" /></td>
	          </tr>
	          
	          <tr>
	            <td>系统工作模式</td>
	            <td>
	            	<select type="select" name="env" value="<%=(sc!=null?sc.getEnv():"")%>">
					    <option value='pro' <%=("pro".equals(sc.getEnv()))?"SELECTED":""%> >生产</option>
						<option value='dev' <%=("dev".equals(sc.getEnv()))?"SELECTED":""%>  >测试</option>
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