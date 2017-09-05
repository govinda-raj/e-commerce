<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String msg=(String)request.getAttribute("msg");
if(msg!=null){ %>
	<h2><%=msg %></h2>
<%} %>
	<form action="/home" method="post">
	Enter Email<input type="text" name="email"><br>
	<input type="submit" name=login value="Login">
	</form>
</body>
</html>