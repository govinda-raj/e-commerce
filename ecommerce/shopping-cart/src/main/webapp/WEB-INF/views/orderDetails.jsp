<%@page import="com.coviam.model.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
String email=(String)request.getAttribute("email");
	List<Cart> cartList=(List<Cart>)request.getAttribute("cartList"); %>
</head>
<body>
<center>

<% 	if(cartList.size()==0){%>
	<h3>you have not selected any products</h3>
	<%}else{ %>
	<h3>Order Details</h3>
	<form action="confirmOrder">
	<table border="2">
	<tr>
	<td colspan="2">User Email</td>
	<td colspan="2"><%=email %></td>
	</tr>
	<tr>
	<td>Product Id</td>
	<td>Product Name</td>
	<td>Product Quantity</td>
	<td>Product Price</td>
	</tr>
	<% double sum=0;
	for(Cart cart:cartList){
		double productPrice=cart.getProductQuantity()*cart.getProductPrice();
		sum=sum+productPrice; %>
	<tr>
	<td><%=cart.getProductId() %></td>
	<td><%=cart.getProductName() %></td>
	<td><%=cart.getProductQuantity() %></td>
	<td><%=productPrice %></td>
	</tr>
	<%} %>
	<tr>
	<td colspan="2">Total Price</td>
	<td colspan="2"><%=sum %></td>
	</tr>
	</table>
	<input type="submit" value="Confirm Order" name="submit">
	</form>
	<%} %>
</center>
</body>
</html>