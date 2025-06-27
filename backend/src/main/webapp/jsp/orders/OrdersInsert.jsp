<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, orders.bean.ShipBean"%>
    
 <%
    List<ShipBean> shipList = (List<ShipBean>) request.getAttribute("shipList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單新增</title>
<style type="text/css">
body{
background-color:#F2F2F2;
}
</style>
<script>
function insertFee() {
	const select = document.getElementById("ship");
	const fee = parseInt(select.options[select.selectedIndex].dataset.fee || 0);
	document.getElementById("ship_fee").value = fee;

	const total = parseInt(document.getElementById("total_price").value || 0);
	document.getElementById("final_price").value = total + fee;
}
</script>
</head>
<body>
<div align="center">
<h2>新增訂單資料</h2>
<form action="InsertOrders" method="post">
<table>
<!--<tr><td>訂單編號<td><input type="text" name="orders_id"></tr> -->
<!--<tr><td>會員編號<td><input type="text" name="mem_id"></tr>-->
<tr><td>訂單總金額<td><input type="hidden" id="total_price" name="hidden_total_price" oninput="insertFee()" required></tr>
<tr><td>運送方式:<td>
<select id="ship" name="ship_id" onchange="insertFee()" required>
	<option value="">--請選擇運送方式--</option>
	<% for(ShipBean s : shipList) { %>
		<option value="<%= s.getShipId() %>" data-fee="<%= s.getShipFee() %>">
			<%= s.getShipName() %>
		</option>
		<% } %>
</select><br>
</td></tr>
<tr><td>運費:<td> <input type="text" id="ship_fee" name="ship_fee" readonly></tr>
<tr><td>應付金額:<td> <input type="text" id="final_price" name="final_price" readonly></tr>
<tr><td><button type="submit" >新增</button></td></tr>
</table>
</form>
</div>

<script type="text/javascript">
function updateTotalPrice() {
    let total = 0;
    document.querySelectorAll("[id^='subtotal_']").forEach(span => {
        total += parseInt(span.textContent || 0);
    });
    document.getElementById("hidden_total_price").value = total;
}
</script>
</body>
</html>