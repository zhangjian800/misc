<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>

<!<%@ page import="com.dolphin.webapp.sms.rule" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	List result = (List)request.getAttribute("RuleList");
%>
<htm	
	RuleSearchVO searchVO =  (RuleSearchVO)request.getAttribute("RuleSearchVO");
	List versionList = (List)request.getAttribute("VersionList");
	List provinceList = (List)request.getAttribute("ProvinceList");
	tml>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />    
    <title>DataTables live example</title>
    <style type="text/css">
      @import "/dolphin/css/page.css";
      @import "/dolphin/css/table.css";
    </style>
    <script type="text/javascript" language="javascript" src="/dolphin/js/jquery-1.6.1.js"></script>
    <script type="text/javascript" language="javascript" src="/dolphin/js/jquery.dataTables.min.js"></script>
  </head>
  <body 	<script type="text/javascript"> 
		 var submit = function(){
			$("#searchForm").submit();
		 };
	</script>    
  </head>
  <body id="tables">
<form name="searchForm" id="appForm" action="/dolphin/listAllRules.do" method="POST">
  
    <div id="container">
		<table cellpadding="0" cellspacing="0" border="0" class="display" id="search">    
    	  <h1>查询条件:</h1>
	          <tr>
	            <td>规则类型</td>
	            <td>
	            	<% String apptype = searchVO!=null?searchVO.getApptype():""; %>	

	            	<select type="select" name="apptype">
					    <option value=''>所有</option>
					    <option value='0' <%=("0".equals(apptype)?"SELECTED":"")%>>系统</option>
   					    <option value='1'  <%=("1".equals(apptype)?"SELECTED":"")%>>广告</option>
						<option value='2' <%=("2".equals(apptype)?"SELECTED":"")%>>计费</option>
	            	</select>
				</td>
			  </tr>
			  <tr>
				<td>状态</td>
	            <td>
	            	<% String status = searchVO!=null?searchVO.getStatus():""; %>	

	            	<select type="select" name="status">
					    <option value=''>所有</option>
					    <option value='Enable' <%=("Enable".equals(status)?"SELECTED":"")%>>启用</option>
   					    <option value='Disable'  <%=("Disable".equals(status)?"SELECTED":"")%>>停用</option>
	            	</select>
				</td>
	          </tr>	       

	          <tr>
	            <td>省份</td>
	            <td>
	            	<select name="province" id="province" value='<%=(searchVO!=null?searchVO.getProvince():"")%>'>
						<option value=''>==请选择===</option>
						<% if(provinceList!=null && provinceList.size()>0) {
		        			for(int i=0; i < provinceList.size(); i++) {
								HashMap map = (HashMap)provinceList.get(i);
								String province = (searchVO!=null && searchVO.getProvince()!=null)?searchVO.getProvince():"";
								boolean isSelected = province.equals((String)map.get("provicecode"));
						%>
		        				<option value='<%=map.get("provicecode")%>'  <%=(isSelected?"SELECTED":"")%>><%=map.get("provicedesc")%></option>
						<% 		
		           			} 
							}
						%>
	            	</select>	            	
	            </td>
	          </tr>	 
			  
	          <tr>
	               <td>版本</td>
	            <td>
	            	<select name="version" value="<%=(searchVO!=null?searchVO.getVersion():"")%>">
							<option value=''>==请选择===</option>
		        	<% if(versionList!=null) {
		        			for(int i=0; i < versionList.size(); i++) {
								HashMap map = (HashMap)versionList.get(i);
								String version = (searchVO!=null && searchVO.getVersion()!=null)?searchVO.getVersion():"";
								boolean isSelectet = version.equals((String)map.get("versioncode"));								
		        	%>
		        				<option value='<%=map.get("versioncode")%>' <%=(isSelectet?"SELECTED":"")%>><%=map.get("versioncode")%></option>
		        	<% 		
		           			} 
		        	   }
		        	%>
	            	</select>
	             <tr>
	            <td colspan="4" align="Right"><input type="button" value="�left"><input type="button" value="查询" onclick="submit()"></td>
	          </tr>
			iner">
      <h1>查询结果:</h1>
      <table cellpadding="0" cellspacing="0" border="0" class="display" id="result">
        <thead>
          <tr>
            <th>规则代码</th>
            <th>规则类型</th>
            <th>省份</th>
            <th>城市</th>
  		  <th>编号</th
            <-    <th>版本</th>
            <th>Interval</th>
           <!-- <th>距离第一次�STATUS</th>            >距离第一次上访天数</th> -->
            <th>应用</th>
            <th>修改</th>
          </tr>
        </thead>
        <tbody>
        	<% if(r  <th>删除
        	<% if(result!=null) {
        			for(int i=0; i < result.size(); i++) {
        				boolean isOdd = (i%2==0) ;
        				Rule map = (Rule)result.get(i);
						
						String ruleType = map.getRuletype();
						boolean isSystemRule= "rule4fstpriority".equals(ruleType);
        	%>
        				<tr class='<%=(isOdd?"odd gradeX":"even g						 gradeC")%>'>	
            				<td align="center"><%=map.getRulecode()%></td>
            				<td align="center"><%=isSystemRule?"系统规则":"一般规则"%></t							<td align="center"><%=map.getRuleID()%></td>/t%
            					String apptype2 = map.getApptype();
            					String ruledesc = "系统";
            					if("1".equals(apptype2)){
            						ruledesc = "广告";
            					}
            					if("2".equals(apptype2)){
            						ruledesc = "计费";
            					}  
            				%>
            				<td align="center"><%=ruledesc)%></td>
           					<td align="cente	
            					String city = map.getCity();
            					if("rule4multicity".equals(map.getRuletype())){
            						city = "多城市";
            					}		<td align="center"><%=map.getCity()%></td>
    -roductcode()%></td>
							<td align="center"><%=map.getVersion()%></td>
            				<td acity><%=map.getInterval()%></td>
            				<!--<td align="center"><%=map.getGapdayStatus()%></td>							    				<!--<td align="center"><%=map.getGapdays()%></td> -->
            				<td align="center"><%=map.getAppID()%></td>
            				<td align="center"><a href="/dolphin/updateRule.do?ru-<%=map.getAppcode()%>-<%=map.getAppfilenameo?ruleID=<%=map.getRuleID()%>&type=<%=map.getRuletype()%>">修改</a></td>  

        				</tr>
        	<% 		
           			} 
        	   }
        	%>
							<td align="center"><a href="/dolphin/disableRule.do?ruleID=<%=map.getRuleID()%>&type=<%=map.getRuletype()%>">删除</a></td>        </tbody>
      </table>
    </div>
  <script>
$(document).ready(function() {
	$('#result').dataTable();
} );
</script>
</body>
</html>
</form>  
</body>
</html>