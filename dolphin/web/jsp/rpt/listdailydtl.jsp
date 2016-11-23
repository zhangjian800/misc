<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>
<%@ page import="com.dolphin.common.utils.lang.DateUtils" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	List result = (List)request.getAttribute("AccessList");
	AccessSearchVO searchVO =  (AccessSearchVO)request.getAttribute("AccessSearchVO");
	List<Rule> ruleList = (List)request.getAttribute("chargeRuleList");
	
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
    <script type="text/javascript" language="javascript" src="/dolphin/js/jquery.dataTables.min.js"></script>
    
  
	<script type="text/javascript"> 
		 var submit = function(){
			$("#searchAccessForm").submit();
		 };
		 
	</script>    
  </head>
  <body id="tables">
	<form name="searchAccessForm" id="appForm" action="/dolphin/listDailyDtlCharge.do" method="POST">
  
    <div id="container">
    	  <h1>查询条件:</h1>
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
	          <tr>
	            <td>当日日期</td>
	            <td><input type="text" name="currdate" value='<%=((searchVO!=null && searchVO.getCurrdate()!=null)?searchVO.getCurrdate():"")%>'></td>
               	<td>规则</td>
	            <td>
	            	<select name="ruleID" value="">
	            		<option value=''>=====请选择====</option>	            	
		        	<% if(ruleList!=null) {
		        			for(int i=0; i < ruleList.size(); i++) {
								Rule map = (Rule)ruleList.get(i);
								int ruleID = searchVO.getRuleID();
								boolean isSelectet = ruleID == map.getRuleID(); 		
		        	%>
		        				<option value='<%=map.getRuleID()%>' <%=(isSelectet?"SELECTED":"")%>><%=map.getRulecode()%></option>
		        	<% 		
		           			} 
		        	   }
		        	%>
	            	</select>
	            </td>
	          </tr>	  
        	<tr>
	            <td>IMSI</td>
	            <td><input type="text" name="imsi" value='<%=((searchVO!=null && searchVO.getImsi()!=null)?searchVO.getImsi():"")%>'></td>
	            <td>最大多少条数据</td>
	            <td><input type="text" name="maxlimit" value='<%=(searchVO!=null?searchVO.getMaxlimit():"")%>'>            	
	            </td>
	          </tr>	
	          <tr>
	            <td colspan="4" align="left"><input type="button" value="查询" onclick="submit()"></td>
	          </tr>
    	   </table>
    </div>
    <div id="container">
      <h1>查询结果:</h1>
      <table cellpadding="0" cellspacing="0" border="0" class="display" id="result">
        <thead>
          <tr>
            <th>编号</th>
            <!--<th>省份</th>-->
            <th>省份-城市</th>
            <th>IMSI</th>
            <th>手机型号</th>	
            <th>版本</th>
            <th>状态</th>
            <th>上访时间</th>
            <th>interval</th>
            <th>详细</th>
          </tr>
        </thead>
        <tbody>
        	<% if(result!=null) {
        			for(int i=0; i < result.size(); i++) {
        				boolean isOdd = (i%2==0) ;
        				Access map = (Access)result.get(i);
        	%>
        				<tr class='<%=(isOdd?"odd gradeX":"even gradeC")%>'>	
            				<td align="center"><%=map.getAccessID()%></td>
            				<td align="center"><%=map.getProvince()%>-<%=map.getCity()%></td>
            				<td align="center"><%=map.getImsi()%></td>
            				<td align="center"><%=map.getProductType()%></td>
            				<td align="center"><%=map.getVersion()%></td>
            				<td align="center"><%=map.getResponseStatus()%></td>
            				<td align="center"><%=DateUtils.date2String(map.getAccessTime(), "yyyy-MM-dd HH:mm:ss")%></td>
            				<td align="center"><%=map.getInterval()%></td>
            				<td align="center"><a href="/dolphin/viewAccessResponse.do?accessID=<%=map.getAccessID()%>">查看</a></td>  
        				</tr>
        	<% 		
           			} 
        	   }
        	%>
        </tbody>
      </table>
    </div>
  <script>
$(document).ready(function() {
	$('#result').dataTable();
} );
</script>
</form>
</body>
<script>
<%
	if(searchVO!=null){
%>
		changeCity("<%=searchVO.getCity()%>");
<%
	}
%>
</script>
</html>