<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
登录成功！！
<a href="allUser" >获取当前用户的信息</a>	


	<table  width="800" height="200" border="1" cellpadding="0" cellspacing="0" align="center">
  		<tr>
	        <td>ID </td>
	        <td>用户名</td>
	        <td>密码</td>
        </tr>
  	<%
	  	if(request.getAttribute("id")!= null){
	%>
        <tr>
	        <td><%=request.getAttribute("id") %> </td>
	        <td><%=request.getAttribute("userName") %> </td>
	        <td><%=request.getAttribute("password") %> </td>
        </tr>
		<% }%>
</table>
</body>
</html>