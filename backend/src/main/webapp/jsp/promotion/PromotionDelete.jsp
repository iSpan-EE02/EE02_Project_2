<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>刪除活動</title>
</head>
<body style="backgroud-color:#fdf5e6">
<div align="center">
<h2>刪 除 活 動</h2>
<form action="<%= request.getContextPath() %>/PromotionDelete" method="post">
<table>
<tr><td>活動編號<td><input type="text" name="promotionId">

<tr><td colspan="2" align="center"><input type="submit" value="送出">
</table>
</form>
</div>
</body>
</html>

