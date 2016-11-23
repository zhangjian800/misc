<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>
<%@ page import="com.dolphin.common.utils.lang.DateUtils" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	List result = (List)request.getAttribute("ResultList");
	
	RptSearchVO searchVO =  (RptSearchVO)request.getAttribute("RptSearchVO");
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
			$("#searchForm").submit();
		 };
		 
	</script>    
  </head>
  <body id="tables">
	<form name="searchForm" id="appForm" action="/dolphin/listDailyCharge.do" method="POST">
  
    <div id="container">
    	  <h1>查询条件:</h1>
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
	          <tr>
	            <td>当日日期</td>
	            <td><input type="text" name="currdate" value='<%=((searchVO!=null && searchVO.getCurrdate()!=null)?searchVO.getCurrdate():"")%>'></td>
	          </tr>
	          <tr>
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
		          <tr>
	            <td colspan="4" align="center"><input type="button" value="查询" onclick="submit()"></td>
	          </tr>
    	   </table>
    </div>
    <div id="container">
      <h1>查询结果:</h1>
      <table cellpadding="0" cellspacing="0" border="0" class="display" id="result">
        <thead>
          <tr>
          	<th>规则ID</th>
            <th>规则</th>
            <th>日期</th>
            <th>扣费金额</th>
            <th>状态</th>	
            <th>最近一次更新时间</th>
            <th>明细</th>
          </tr>
        </thead>
        <tbody>
        	<% if(result!=null) {
        			for(int i=0; i < result.size(); i++) {
        				boolean isOdd = (i%2==0) ;
        				RuleAccessDailyCharge map = (RuleAccessDailyCharge)result.get(i);
        	%>
        				<tr class='<%=(isOdd?"odd gradeX":"even gradeC")%>'>
        					<td align="center"><%=map.getRuleID()%></td>	
            				<td align="center"><%=map.getRuledesc()%></td>
            				<td align="center"><%=map.getCurrdate()%></td>
            				<td align="center"><%=map.getChargesum()%></td>
            				<td align="center"><%=map.getStatus()%></td>
            				<td align="center"><%=DateUtils.date2String(map.getUpdatetime(), "yyyy-MM-dd HH:mm:ss")%></td>
            				<td align="center"><a href="/dolphin/listDailyDtlCharge.do?ruleID=<%=map.getRuleID()%>&currdate=<%=map.getCurrdate()%>">查看</a></td>  
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