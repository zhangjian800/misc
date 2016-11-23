<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	List result = (List)request.getAttribute("AppList");
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
  </head>
  <body id="tables">
    <div id="container">
    	  <h1>查询条件:</h1>
    	  <table cellpadding="0" cellspacing="0" border="0" class="display" id="search">
	          <tr>
	            <td>条件1</td>
	            <td><input type="text"></td>
	            <td>条件2</td>
	            <td><input type="text"></td>
	          </tr>
	          <tr>
	            <td>条件3</td>
	            <td><input type="text"></td>
	            <td>条件4</td>
	            <td><input type="text"></td>
	          </tr>
	          <tr>
	            <td colspan="4" align="Right"><input type="button" value="查询"></td>
	          </tr>
    	   </table>
    </div>
    <div id="container">
      <h1>查询结果:</h1>
      <table cellpadding="0" cellspacing="0" border="0" class="display" id="result">
        <thead>
          <tr>
            <th>应用代码</th>
            <th>应用描述</th>
            <!--<th>版本</th>-->类型       <th>应用文件名</th>
            <th>修改</th>
            <th>上传应用文件</th>
            <th>下载</th>
            <th>删除</th>
        !-- <th>下载</th> --   </thead>
        <tbody>
        	<% if(result!=null) {
        			for(int i=0; i < result.size(); i++) {
        				boolean isOdd = (i%2==0) ;
        				App map = (App)result.get(i);
        	%>
        				<tr class='<%=(isOdd?"odd gradeX":"e						String apptype = map.getApptype();
		eC			String apptypedesc = "广告";
						if("2".equals(apptype)){
							apptypedesc = "扣费";
						} else if("3".equals(apptype)){
							apptypedesc = "系统";
						}
		eC")%>'>	
            				<td align="center"><%=map.getAppcode()%></td>
            				<td align="center"><%=map.getAppdesc()%></td>
            				<!-- <td align="center"(apptypedescn()%></td> -->
            				<td align="center"><%=map.getAppfilename()%></td>
            				<td align="center"><a href="/dolphin/updateApp.do?appID=<%=map.getAppID()%>">修改</a></td>  
            				<td align="center">
            					<a href="/dolphin/uploadApp.do?appID=<%=map.getAppID()%>">上传</a>
            				</td>
            				<td align="center">
            					<a href="/dolphin/downloadAppFile.do?appID=<%=map.getAppID()%>">下载</a>
            			>            				
          				
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
</body>
</html>