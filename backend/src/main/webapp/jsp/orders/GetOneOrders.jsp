<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.util.List, orders.bean.OrdersBean ,orders.bean.OrdersItemBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢訂單資料</title>
<style>
body{
background-color:#F2F2F2;
}
table {
    width: 1000px;
  	margin: 20px auto;
  	border-collapse: collapse;
  	border-radius:20px;
  	text-align:center;
}
th{
background-color:#B6B09F;
}

table, th, td {
    border: 1px solid black;
    padding: 8px;
    text-align: center;
}
</style>
</head>
<body>
<div align="center">
<h2>查詢訂單資料</h2>
<table border="1">
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
    	<th>商品名稱</th>
		<th>圖片</th>
    	<th>數量</th>
    	<th>單價</th>
    	<th>小計</th>
		<th>訂單成立日期</th>
		<th>運送方式</th>
		<th>訂單總金額</th>
		<th>運費</th>
		<th>應付金額</tr>
	</tr>
	<% OrdersBean orders = (OrdersBean) request.getAttribute("orders");
    List<OrdersItemBean> itemList = (List<OrdersItemBean>) request.getAttribute("itemList");
	%>
	
<% if (itemList != null && !itemList.isEmpty()) {
    for (OrdersItemBean item : itemList) {
%>
	<tr><td><%= orders.getOrdersId() %></td>
	<td><%= orders.getMemId() %></td>	
    <td><%= item.getProdName() %></td>
	<td><img src="<%= item.getProdImg() %>" width="80" /></td>
    <td><%= item.getQuantity() %></td>
    <td><%= item.getUnitPrice() %></td>
    <td><%= item.getSubtotal() %></td>
	<td><%= orders.getOrdersDate() %></td>
	<td><%= orders.getShipName() %></td>
	<td><%= orders.getTotalPrice() %></td>
	<td><%= orders.getShipFee() %></td>
	<td><%= orders.getFinalPrice() %></td>
	</tr>
	<% } 
	}else{
	%>
		<tr><td colspan="12">查無明細資料</td></tr>
	<% 
	}
	%>
	</table>
</div>
</body>
</html>