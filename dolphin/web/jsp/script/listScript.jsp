<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dolphin.webapp.vo.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	List result = (List)request.getAttribute("ScriptList");
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
            <th>脚本代码</th>
            <th>脚本描述</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
        	<% if(result!=null) {
        			for(int i=0; i < result.size(); i++) {
        				boolean isOdd = (i%2==0) ;
        				Script map = (Script)result.get(i);
        	%>
        				<tr class='<%=(isOdd?"odd gradeX":"even gradeC")%>'>	
            				<td><%=map.getScriptCode()%></td>
            				<td><%=map.getScriptDesc()%></td>
            				<td><a href="/dolphin/updateScript.do?scriptID=<%=map.getScriptID()%>">修改</a></td>            				
        				</tr>
        	<% 		
           			} 
        	   }
        	%>
        </tbody>
        <tfoot>
          <tr>
            <th>脚本代码</th>
            <th>脚本描述</th>
            <th>操作</th>
          </tr>
        </tfoot>
      </table>
    </div>
  <script>
$(document).ready(function() {
	$('#result').dataTable();
} );
</script>
</body>
</html>