<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form  id="formLogin"  action="user/login" method="post">
		用户名 ：
		<input type="text" id="username"  name="username" />
		密码：
		<input type="password" id="password"  name="password" />
		<br>
		<button type="button"  style="margin-left: 300px">取消</button>
		<button type="submit"  style="margin-left:20px;"  >登陆</button>
	</form>
</body>
</html>