<%@page import="com.coviam.model.Product"%>
<%@page import="java.util.List"%>
<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% List<Product> productList=(List<Product>)request.getAttribute("products");
	if(productList.size()==0){%>
	<h3>Sorry the product you are searching for is not there</h3>
	<%}else{ int totalPages=0;
		if(request.getAttribute("totalPages")!=null){
			totalPages=(int)request.getAttribute("totalPages");
	} %>
	<center>
	<table border="2">
	<tr>
	<td>Product Id</td>
	<td>Name</td>
	<td>Description</td>
	<td>Product Category</td>
	<td>Product Price</td>
	<td>Merchant</td>
	</tr>
	<% for(int i=0;i<productList.size();i++){
		Product product=productList.get(i);
		%>
	<tr>
	<form action="/productDetails" method="post">
	<td><input type="submit" name="submit" value=<%=product.getProductId()%>>
	<td><%=product.getProductName() %></td>
	<td><%=product.getProductDescription() %></td>
	<td><%=product.getProductCategory() %></td>
	<td><%=product.getProductPrice() %></td>
	<td><input type="submit" name="submit" value=<%=product.getMerchantName() %>>
	<input type="hidden" name="pid" value=<%=product.getId() %>>
	<input type="hidden" name="mid" value=<%=product.getMerchantId() %>>
	<td><input type="submit" name="submit" value="Add to Cart"></td>
	</form>
	<tr>
	<%} %>
	</table>
	</center>
	
	<form action="getProductsPage">
		<%  for(int index=0;index<totalPages;index++){ %>
		<input type="submit" value=<%=index%> name="submit">
		<% }%>
	</form>
	<%} %>
</body>
</html>