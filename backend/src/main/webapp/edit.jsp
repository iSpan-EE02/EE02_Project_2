<%@ page import="account.bean.Employee" %>
<%
    Employee emp = (Employee) request.getAttribute("emp");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編輯員工</title>
</head>
<body>
<form method="post" action="<%= request.getContextPath() %>/employee">
    <input type="hidden" name="action" value="update" />
    <input type="hidden" name="id" value="<%= emp.getEmpId() %>" />
    姓名：<input type="text" name="name" value="<%= emp.getEmpName() %>"><br>
    信箱：<input type="email" name="email" value="<%= emp.getEmpMail() %>"><br>
    <input type="submit" value="更新">
</form>
</body>
</html>