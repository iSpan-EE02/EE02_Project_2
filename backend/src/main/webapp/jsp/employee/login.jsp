<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工登入</title>
 <!-- 載入 Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 載入 Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <!-- 載入您自訂的 CSS -->
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<div align="center" class="container-fluid">
<h2 class="text-primary">後台登入</h2>
<form action="<%= request.getContextPath() %>/EmployeeServlet" method="post">
    <input type="hidden" name="action" value="login" />
    帳號：<input type="text" name="empMail" required><br><br>
    密碼：<input type="password" name="empPw" required><br><br>
    管理項目：
    <select name="target">
        <option value="member">會員資料</option>
        <option value="employee">員工資料</option>
    </select><br><br>
    <input type="submit" value="登入">
</form>
<% String error = (String) request.getAttribute("error");
   if (error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>
</div>
</body>
</html>