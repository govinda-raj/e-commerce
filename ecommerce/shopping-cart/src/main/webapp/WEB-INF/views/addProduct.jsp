<%--
  Created by IntelliJ IDEA.
  User: coviam
  Date: 21/07/17
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
	<title>Adding Product</title>
</head>
<%List<String> categories=(List<String>)request.getAttribute("categories"); %>
<body>
<h2>Please Enter Product Details</h2>
<% 
String priceMsg=(String)request.getAttribute("priceMsg");
String quantityMsg=(String)request.getAttribute("quantityMsg");
String nameMsg=(String)request.getAttribute("nameMsg");
String descriptionMsg=(String)request.getAttribute("descriptionMsg");
String productIdMsg=(String)request.getAttribute("productIdMsg");
String successMsg=(String)request.getAttribute("successMsg");
String merchantMsg=(String)request.getAttribute("merchantMsg");
%>
<form action="/saveProduct" method="post">
	<%if(successMsg!=null){ %><h3><%=successMsg %><%} %></h3>
<table>
<tr>
<td>Product Name</td>
<td><input name="productName" type="text"></td>
<td><%if(nameMsg!=null){ %><span><%=nameMsg%><%} %></span></td>
</tr>
<tr>
<td>Product Description</td>
<td><input name="productDescription" type="text"></td>
<td><%if(descriptionMsg!=null){ %><span><%=descriptionMsg %><%} %></span></td>
</tr>
<tr>
<td>Product Price</td>
<td><input name="productPrice" type="text"></td>
<td><%if(priceMsg!=null){ %><span><%=priceMsg %><%} %></span></td>
</tr>
<tr>
<td>Product Quantity</td>
<td><input name="productQuantity" type="text"></td>
<td><%if(quantityMsg!=null){ %><span><%=quantityMsg %><%} %></span></td>
</tr>
<tr>
<td>Product SKU</td>
<td><input name="productId" type="text"></td>
<td><%if(productIdMsg!=null){ %><span><%=productIdMsg%><%} %></span></td>
</tr>
	<tr>
		<td>Merchant Email</td>
		<td><input name="merchantEmail" type="text"></td>
		<td><%if(merchantMsg!=null){ %><span><%=merchantMsg%><%} %></span></td>
	</tr>
<tr>
<td>Product Category</td>
<td>
<select name="select">
<% for(int i=0;i<categories.size();i++){  %>
 	<option><%=categories.get(i)%></option>
 	<%}%>
</select>
</td>
</tr>
</table>
<input name="submit" value="Add Product" type="submit">
<input name="submit" value="Cancel" type="submit">
</form>
</body>
</html>