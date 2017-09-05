<%@page import="com.coviam.model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart Details</title>
</head>
<body>
	<% List<Cart> cartList= (ArrayList)session.getAttribute("cartList");
	if(cartList.size()==0){
	%>
	<h3>Empty Cart</h3>
	<% }else{%>
	
	<center>
	<table border="2">
	<tr>
	<td>Product Id</td>
	<td>Product Name</td>
	<td>Qunatity</td>
	<td>Price</td>
	<td>Product Price</td>
	</tr>
	<%for(int i=0;i<cartList.size();i++){
		Cart cart=cartList.get(i);  %>
	<tr>
		<td><%=cart.getProductId() %></td>
		<td><%=cart.getProductName() %></td>
		<td><%=cart.getProductQuantity() %></td>
		<td><%=cart.getProductPrice() %></td>
		
		<form action="/removeFromCart" method="post">
		<td><input type="submit" name="remove" value="Remove from the Cart"></td>
		<input type="hidden" name="cartId" value=<%=cart.getId()%>>
		</form>
		
		<form action="/updateCartItemQuantity" method="post">
		<td><input type="submit" name="update" value="Update Quantity"></td>
		<input type="hidden" name="cartId" value=<%=cart.getId()%>>
		</form>
		
	</tr>
	<%} %>
	</table>
	<%} %>
	<form action="/clearCart">
		<input name="submit" type="submit" value="Clear Cart">
	</form>
	<form action="placeOrder">
		<input name="submit" type="submit" value="Place Order">
	</form>
	</center>
</body>
</html>