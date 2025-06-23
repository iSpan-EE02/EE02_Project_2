<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增員工</title>
</head>
<body>
<form method="post" action="<%= request.getContextPath() %>/employee">
    <input type="hidden" name="action" value="add" />
    姓名：<input type="text" name="name" required><br>
    信箱：<input type="email" name="email" required><br>
    密碼：<input type="password" name="password" required><br>
    <input type="submit" value="新增">
</form>
</body>
</html>