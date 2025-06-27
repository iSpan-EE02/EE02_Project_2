<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, orders.bean.OrdersBean , java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<title>所有訂單資料</title>
<style type="text/css">
body{
background-color:#F2F2F2;
}
table{
	width: 1000px;
  	margin: 20px auto;
  	border-collapse: collapse;
  	border-radius:20px;
  	text-align:center;
}

th{
background-color:#B6B09F;
}


</style>
</head>
<body>
	<div align="center">
	<h1>訂單列表管理系統</h1>
	<table border="1">
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>訂單成立日期</th>
		<th>運送方式</th>
		<th>訂單總金額</th>
		<th>運費</th>
		<th>應付金額</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<% List<OrdersBean> ordersList = (ArrayList<OrdersBean>)request.getAttribute("ordersList");
	for(OrdersBean orders : ordersList) { %>
	<tr><td><%= orders.getOrdersId() %></td>
	<td><%= orders.getMemId() %></td>
	<td><%= orders.getOrdersDate() %></td>
	<td><%= orders.getShipName() %></td>
	<td><%= orders.getTotalPrice() %></td>
	<td><%= orders.getShipFee() %></td>
	<td><%= orders.getFinalPrice() %></td>
	<td><a href="UpdateOrders?orders_id=<%= orders.getOrdersId() %>"><i class="fa-solid fa-pen-to-square" style="color: #948979;"></i></a></td>
	<td><a href="DeleteOrders?orders_id=<%= orders.getOrdersId() %>" onclick="return confirm('確定要刪除嗎？')"><i class="fa-solid fa-trash-can" style="color: #f10909;"></i></a></td><%  } %>
	</tr>
	</table>
	<h3>共<%= ordersList.size() %>筆訂單資料</h3>
	
	<form action="OrdersGetAllProd" method="get">
  <button type="submit">查看商品頁面</button>
</form>
	</div>
</body>
</html>