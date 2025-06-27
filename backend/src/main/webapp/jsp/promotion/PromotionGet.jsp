<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動資訊</title>
</head>
<body style="backgroud-color:#fdf5e6">
<div align="center">
<h2>活 動 資 訊</h2>
<jsp:useBean id="prom" scope="request" class="promotion.bean.promotionBean" />
<table>
<tr><td>活動名稱<td><input type="text" disabled value="<%= prom.getTitle() %>">
<tr><td>活動描述<td><input type="text" disabled value="<%= prom.getDescription() %>">
<tr><td>活動類型<td><input type="text" disabled value="<%= prom.getType() %>">
<tr><td>效期類型<td><input type="text" disabled value="<%= prom.getValidityType() %>">
<tr><td>使用效期開始<td><input type="date" disabled value="<%= prom.getValidityFrom() %>">
<tr><td>使用效期結束<td><input type="date" disabled value="<%= prom.getValidityTo() %>">
<tr><td>領取後有效天數<td><input type="text" disabled value="<%= prom.getValidityDays() %>">
<tr><td>是否啟用<td><input type="text" disabled value="<%= prom.getActive() %>">
<tr><td>備註<td><input type="text" disabled value="<%= prom.getNote() %>">
</table>
</div>
</body>
</html>
