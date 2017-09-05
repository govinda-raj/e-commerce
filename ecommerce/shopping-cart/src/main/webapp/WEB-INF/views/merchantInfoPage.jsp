<%@ page import="com.coviam.model.Merchant" %><%--
  Created by IntelliJ IDEA.
  User: coviam
  Date: 10/08/17
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Merchant Info</title>
</head>
<body>
<% Merchant merchant=(Merchant) request.getAttribute("merchant");%>

<form action="/" >
    <tr><td> Merchant Email : </td><td><%=merchant.getEmailId() %></td></tr>
    <tr><td>Merchant Name : </td><td><%=merchant.getMerchantName() %></td></tr>
    <tr><td>Merchant Rating :</td><td><%=merchant.getRating() %></td></tr>
    <input type="submit"  name="submit" value="Back">
</form>

</body>
</html>
