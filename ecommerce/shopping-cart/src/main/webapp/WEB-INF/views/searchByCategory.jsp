<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/searchProductByCategory">
	<%List<String> categories=(List<String>)request.getAttribute("categories"); %>
 	Select Category to search product
 	<select name="select">
 	<% for(int i=0;i<categories.size();i++){  %>
 	<option><%=categories.get(i)%></option>
 	<%}%>
 	</select>
 	<input type="submit" value="Search">
  </form>
</body>
</html>