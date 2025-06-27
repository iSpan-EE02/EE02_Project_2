<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, orders.bean.ShipBean"%>
    <%
    List<ShipBean> shipList = (List<ShipBean>) request.getAttribute("shipList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更改訂單資料</title>

<style type="text/css">
.btn {
  -webkit-border-radius: 28;
  -moz-border-radius: 28;
  border-radius: 28px;
  font-family: Arial;
  color: #ffffff;
  font-size: 20px;
  background: #ad8d77;
  padding: 10px 20px 10px 20px;
  text-decoration: none;
}

.btn:hover {
  background: #bfb08b;
  text-decoration: none;
}
</style>
<script>
function updateFinalPrice(){
 const select = document.getElementById("ship");
 const fee = parseInt(select.options[select.selectedIndex].dataset.fee || 0);
 document.getElementById("ship_fee").value = fee;
 
 const total = parseInt(document.getElementById("total_price").value || 0);
 document.getElementById("final_price").value = total + fee;

}
window.onload = function() {
	  updateFinalPrice();
	};
</script>
</head>
<body>
<div align="center">
<jsp:useBean id="orders" scope="request" class="orders.bean.OrdersBean" />
<h2>更新訂單資料</h2>
<form action="<%= request.getContextPath() %>/UpdateOrders" method="post">
<table>
<tr><td>訂單編號(不可修改)
	<td><input type="text" readonly name="orders_id" value="<%= orders.getOrdersId() %>">
<tr><td>會員編號(不可修改)<td><input type="text" readonly name="mem_id" value="<%= orders.getMemId() %>">
<tr><td>訂單總金額(不可修改)<td><input type="text" readonly id="total_price" name="total_price" value="<%= orders.getTotalPrice() %>" oninput="updateFinalPrice()">
<tr><td>運送方式:<td>
<select id="ship" name="ship_id" onchange="updateFinalPrice()" required>
	<option value="">--請選擇運送方式--</option>
	<% for(ShipBean s : shipList) { %>
		<option value="<%= s.getShipId() %>" data-fee="<%= s.getShipFee() %>" 
  <%= s.getShipId() == orders.getShipId() ? "selected" : "" %>>
  <%= s.getShipName() %>
</option>
		<% } %>
</select><br>
</td></tr>
<tr><td>運費:<td> <input type="text" id="ship_fee" name="ship_fee" readonly oninput="updateFinalPrice()"></tr><br>
<tr><td>應付金額:<td> <input type="text" id="final_price" name="final_price" readonly></tr>
</table>
<button type="submit" class="btn">確認修改</button>
</form>
</div>

</body>
</html>