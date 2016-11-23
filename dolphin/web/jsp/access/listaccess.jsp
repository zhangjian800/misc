<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>
<%@ page import="com.dolphin.common.utils.lang.DateUtils" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	List result = (List)request.getAttribute("AccessList");
	
	AccessSearchVO searchVO =  (AccessSearchVO)request.getAttribute("AccessSearchVO");
	List versionList = (List)request.getAttribute("VersionList");
	List provinceList = (List)request.getAttribute("ProvinceList");
	List produdctList = (List)request.getAttribute("ProdudctList");
	
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
		 
		 var changeCity = function(cityCode){
		 	var province = $("#province").val();	
		 	if(province=="" || province.length==0){
				$("#city").empty();
				return;	
		 	}		
			$("#city").empty();
			
			$("#city").append("<option value=''>===请选择=====</option");  
			
			if(province == ""){
				console.log("SET System rule.");
				return; 
			} else {
							
				$.ajax({  
					type:"get",  
					dataType:"json",  
					contentType:"application/json;charset=utf-8",  
					url:"/dolphin/LoadCodeTableAction.do?type=CityByProvince&arg1="+province,  
					success:function(result){  
							$.each(result,function(index,value){  
								console.log($("#city").html());
								if(value.citycode==cityCode){
									$("#city").append("<option value='"+value.citycode+"' SELECTED>"+value.citydesc+"</option>");  
								}else{
									$("#city").append("<option value='"+value.citycode+"'>"+value.citydesc+"</option>");
								}
							})  
					},  
					error : function(XMLHttpRequest, textStatus, errorThrown) {  
								alert(errorThrown); 
							},  
					async:false             //false表示同步  
				});  
				

			}


		 };
	</script>    
  </head>
  <body id="tables">
	<form name="searchAccessForm" id="appForm" action="/dolphin/listAccessResult.do" method="POST">
  
    <div id="container">
    	  <h1>查询条件:</h1>
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
	          <tr>
	            <td>IMSI</td>
	            <td><input type="text" name="imsi" value='<%=((searchVO!=null && searchVO.getImsi()!=null)?searchVO.getImsi():"")%>'></td>
	            <td>上访月（201301)</td>
	            <td><input type="text" name="yearmonth" value='<%=((searchVO!=null && searchVO.getYearmonth()!=null)?searchVO.getYearmonth():"")%>'></td>
	          </tr>
	          <tr>
	            <td>上访开始时间(2013-01-01)</td>
	            <td><input type="text" name="beginAccessTime" value='<%=((searchVO!=null && searchVO.getBeginAccessTime()!=null)?searchVO.getBeginAccessTime():"")%>'></td>
	            <td>上访结束始时间(2013-01-01)</td>
	            <td><input type="text" name="endAccessTime" value='<%=((searchVO!=null && searchVO.getEndAccessTime()!=null)?searchVO.getEndAccessTime():"")%>'></td>
	          </tr>
	          <tr>
	            <td>上访处理状态</td>
	            <td>
	            	<% String status = searchVO!=null?searchVO.getRespstatus():""; %>	

	            	<select type="select" name="respstatus">
					    <option value=''>所有</option>
					    <option value='SUCCESS' <%=("SUCCESS".equals(status)?"SELECTED":"")%>>成功</option>
   					    <option value='SUCCESS2'  <%=("SUCCESS2".equals(status)?"SELECTED":"")%>>纠错</option>
						<option value='INVALID_INPUT' <%=("INVALID_INPUT".equals(status)?"SELECTED":"")%>>非法输入</option>
					    <option value='NORULE' <%=("NORULE".equals(status)?"SELECTED":"")%>>无规则</option>
					    <option value='NOADSDATA' <%=("NOADSDATA".equals(status)?"SELECTED":"")%>>无广告数据</option>
					    <option value='MAXLIMIT' <%=("MAXLIMIT".equals(status)?"SELECTED":"")%>>超过最大限制</option>
						<option value='EXCEPTION1' <%=("EXCEPTION1".equals(status)?"SELECTED":"")%>>异常1</option>
						<option value='EXCEPTION2' <%=("EXCEPTION2".equals(status)?"SELECTED":"")%>>异常2</option>
						<option value='smsiBlock' <%=("smsiBlock".equals(status)?"SELECTED":"")%>>IMSI拦截</option>
						<option value='productBlock' <%=("productBlock".equals(status)?"SELECTED":"")%>>机型拦截</option>
						<option value='NONWORKINGTIME' <%=("NONWORKINGTIME".equals(status)?"SELECTED":"")%>非工作时间拦截</option>
	            	</select>
				</td>
	            <td></td>
	            <td></td>
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
                <td>城市</td>
	            <td>
	            	<select name="city" id="city" value="<%=(searchVO!=null?searchVO.getCity():
	          </tr>	 
			  
	          <tr style="display:none">
	      y():"")%>">
						<option value=''>==请选择===</option>
	            	</select>	            	
	            </td>
	          </tr>	 
			  
	          <tr>
	            <td>机型</td>
	            <td>
	            	<select name="productcode" value="<%=(searchVO!=nu<option value=''>==请选择===</option>
		        	<% if(produdctList!=null) {
		        			for(int i=0; i < produdctList.size(); i++) {
								PhoneProduct map = (PhoneProduct)produdctList.get(i);
								String productCode = (searchVO!=null && searchVO.getProductcode()!=null) ?searchVO.getProductcode():"";
								boolean isSelected = productCode.equals(map.getProductcode());								
		        	%>
		        				<option value='<%=map.getProductcode()%>' <%=(isSelected?"SELECTED":"")%>><%=map.getProductdesc()%></option>
		        	<% 		
		           			} 
		        	   }
		        	%>
	            	</select>	            	
	            </td>
	               <td>版本</td>
	            <td>
	            	<select name="version" value="<%=(searchVO!=null?searchVO.getVers
	            	  </tr>	 
	           <tr>ion():"")%>">
							<option value=''>==请选择===</option>
		        	<% if(versionList!=null) {
		        			for(int i=0; i < versionList.size(); i++) {
								HashMap map =             hMap)versionList.get(i);
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