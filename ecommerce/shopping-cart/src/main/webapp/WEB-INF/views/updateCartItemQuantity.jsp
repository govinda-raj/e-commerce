<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%int cartId=(int)request.getAttribute("cartId"); %>
<form action="/updateCart" method="post">
	Enter new Quantity<input type="text" name="productQunatity">
	<input type="submit" name="Update"> 
	<input type="hidden" value=<%=cartId%> name="cartId">
</form>
</body>
</html>