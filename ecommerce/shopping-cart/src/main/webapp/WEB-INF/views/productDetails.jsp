<%@page import="com.coviam.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Product product=(Product)request.getAttribute("product"); 
	%>
	
	<form action="/addToCart" method="post">
	<center>
		<table>
			<tr>
				<td>Product Id:</td>
				<td><%=product.getProductId()%></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><%=product.getProductName()%></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><%=product.getProductDescription()%></td>
			</tr>
			<tr>
				<td>Product Category:</td>
				<td><%=product.getProductCategory()%></td>
			</tr>
			<tr>
				<td>Product Price:</td>
				<td><%=product.getProductPrice()%></td>
			</tr>
			<tr>
				<td>Merchant:</td>
				<td><%=product.getMerchantName()%></td>
			</tr>
			<tr>
				<td>Enter quantity:</td>
				<td><input name="quantity" type="text"></td>
			</tr>
			 <tr>
				<td><input type="submit" name="submit" value="Add to Cart"></td>
			</tr>
		</table>
		</center>
		<input name="id" type="hidden" value="<%=product.getId()%>">
		<input name="mid" type="hidden" value="<%=product.getMerchantId()%>">
	</form>
</body>
</html>