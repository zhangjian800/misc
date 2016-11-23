<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>
<%@ page import="com.dolphin.common.utils.lang.DateUtils" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	List result = (List)request.getAttribute("PhoneChargeList");

	ConfirmChargeRptSearchVO searchVO =  (ConfirmChargeRptSearchVO)request.getAttribute("ConfirmChargeRptSearchVO");
	List versionList = (List)request.getAttribute("VersionList");
	List provinceList = (List)request.getAttribute("ProvinceList");
	
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
	<form name="searchAccessForm" id="appForm" action="/dolphin/listchargeconfirm.do" method="POST">
  
    <div id="container">
    	  <h1>查询条件:</h1>
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
	          <tr>
	            <td>IMSI</td>
	            <td><input type="text" name="imsi" value='<%=((searchVO!=null && searchVO.getImsi()!=null)?searchVO.getImsi():"")%>'></td>
	          </tr>
	          <tr>
	            <td>开始时间(2013-01-01)</td>
	            <td><input type="text" name="beginUpdateTime" value='<%=((searchVO!=null && searchVO.getBeginUpdateTime()!=null)?searchVO.getBeginUpdateTime():"")%>'></td>
	            <td>结束时间(2013-01-01)</td>
	            <td><input type="text" name="endUpdateTime" value='<%=((searchVO!=null && searchVO.getEndUpdateTime()!=null)?searchVO.getEndUpdateTime():"")%>'></td>
	          </tr>

	          <tr>
	            <td>省份</td>
	            <td>
	            	<select name="province" id="province" value='<%=(searchVO!=null?searchVO.getProvince():"")%>' onchange="changeCity()" >
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
	            </td>
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
	            </td>
	          </tr>	  
	          
        	<tr>
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
            <th>电话</th>
            <th>访问ID</th>
            <th>确认内容</th>
            <th>确认时间</th>            
            <th>省份-城市</th>
            <th>IMSI</th>
            <th>版本</th>
            <th>系统确认时间</th>
          </tr>
        </thead>
        <tbody>
        	<% if(result!=null) {
        			for(int i=0; i < result.size(); i++) {
        				boolean isOdd = (i%2==0) ;
        				PhoneCharge map = (PhoneCharge)result.get(i);
        	%>
        				<tr class='<%=(isOdd?"odd gradeX":"even gradeC")%>'>
            				<td align="center"><%=map.getMobile()%></td>
            				<td align="center"><%=map.getLinkid()%></td>
            				<td align="center"><%=map.getContent()%></td>
            				<td align="center"><%=map.getReceivetime()%></td>
            				<td align="center"><%=map.getProvince()%>-<%=map.getCity()%></td>
            				<td align="center"><%=map.getImsi()%></td>
            				<td align="center"><%=map.getVersion()%></td>
            				<td align="center"><%=DateUtils.date2String(map.getUpdatetime(), "yyyy-MM-dd HH:mm:ss")%></td>
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