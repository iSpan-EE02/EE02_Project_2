<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, orders.prod.bean.OrdersProdBean"%>
<%
	List<OrdersProdBean> prodList = (List<OrdersProdBean>)request.getAttribute("OrdersProdList");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品列表</title>
<style>
button {
    width: 25px;
    height: 25px;
}
input[type="text"] {
    text-align: center;
}

td input, td select {
    margin-left: 10px;
    margin-right: 10px;
}

</style>
</head>
<body>
<div align=center>
<h2>商品列表</h2>
<form action="InsertOrders" method="post">
    <table border="1">
        <tr><th>商品名稱</th><th>商品圖片</th><th>價格</th><th>數量</th><th>小計</th></tr>
        <%
        for (OrdersProdBean prod : prodList) {
        %>
        <tr>
            <td><%= prod.getProdName() %></td>
            <td><img src="<%= request.getContextPath() + prod.getProdImg() %>" width="100"></td>
            <td id="price_<%= prod.getProdId() %>"><%= prod.getProdPrice() %></td>
            <td>
    			<button type="button" onclick="changeQuantity('<%=prod.getProdId()%>', -1)">-</button>
    			<input type="text" name="quantity_<%=prod.getProdId()%>" id="quantity_<%=prod.getProdId()%>" value="0" size="2" readonly>
    			<button type="button" onclick="changeQuantity('<%=prod.getProdId()%>', 1)">+</button>
    			<input type="hidden" name="product_id_<%=prod.getProdId()%>" value="<%=prod.getProdId()%>">
			</td>
			<td><span id="subtotal_<%= prod.getProdId() %>">0</span></td>
        </tr>
        <%
        }
        %>
        <tr>
    <td colspan="4" align="right"><b>總金額：</b></td>
    <td><span id="totalAmount">0</span></td>
    <input type="hidden" id="total_price" name="total_price" value="0">
</tr>
    </table>
    <br>
    <tr>
    	<td colspan="5" style="text-align:center;">
    	<table style="width:100%;">
    	<tr>
		<label for="ship">選擇運送方式：</label>
		<select id="ship" name="ship_id" onchange="updateShipping()">
		<option value="" disabled selected>選擇運送方式</option>
    	<option value="1" data-fee="100">宅急便</option>
    	<option value="2" data-fee="60">超商店到店</option>
		</select>
		<br><br>
		<label>運費：</label>
		<input type="text" id="ship_fee" name="ship_fee" value="100" readonly>
		<br><br>
		<label>應付金額：</label>
		<input type="text" id="final_price" name="final_price" value="0" readonly>
    		<input type="submit" value="提交訂單">   		
    	</td>
    </tr>
    </table>
    </td>
    </tr>
</form>
</div>
<script>
function changeQuantity(prodId, delta) {
    const input = document.getElementById("quantity_" + prodId);
    const subtotalSpan = document.getElementById("subtotal_"+prodId);
    const priceElement = document.getElementById("price_" + prodId);
    const unitPrice = parseInt(priceElement.innerText.trim())

    let quantity = parseInt(input.value) || 0;
    quantity += delta;
    if (quantity < 0) quantity = 0;
    
    input.value = quantity;
    subtotalSpan.textContent = quantity * unitPrice;
    
    updateTotalAmount();
}
function updateTotalAmount() {
    let total = 0;
    const subtotals = document.querySelectorAll('[id^="subtotal_"]');
    subtotals.forEach(span => {
        total += parseInt(span.textContent) || 0;
    });
    
    const fee = parseInt(document.getElementById("ship_fee").value) || 0;
    const finalPrice = total + fee;
    
    document.getElementById("totalAmount").textContent = total;
    document.getElementById("final_price").value = finalPrice;
    
    document.getElementById("total_price").value = total;
}
function updateShipping() {
    const select = document.getElementById("ship");
    const fee = parseInt(select.options[select.selectedIndex].dataset.fee);
    document.getElementById("ship_fee").value = fee;

    // 重新更新總金額
    updateTotalAmount();
}

</script>
</body>
</html>