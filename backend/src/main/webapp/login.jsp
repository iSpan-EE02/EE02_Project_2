<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工登入</title>
</head>
<body>
<h2>員工登入</h2>
<form action="<%= request.getContextPath() %>/employee" method="post">
    <input type="hidden" name="action" value="login" />
    帳號（信箱）：<input type="text" name="email" required><br>
    密碼：<input type="password" name="password" required><br>
    <input type="submit" value="登入">
</form>
<% String error = (String) request.getAttribute("error");
   if (error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>
</body>
</html>