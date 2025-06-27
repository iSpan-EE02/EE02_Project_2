<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="orders.prod.bean.OrdersProdBean"%>
   <% 
   	OrdersProdBean prod = (OrdersProdBean) request.getAttribute("prod");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>商品詳細資料</h2>
<table border="1">
    <tr><th>商品名稱</th><td><%= prod.getProdName() %></td></tr>
    <tr><th>商品圖片</th><td><img src="<%= request.getContextPath() + prod.getProdImg() %>" width="100"></td></tr>
    <tr><th>價格</th><td><%= prod.getProdPrice() %></td></tr>
</table>
</body>
</html>