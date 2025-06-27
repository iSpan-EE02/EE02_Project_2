<%@ page import="account.bean.Employee" %>
<%@ page import="account.bean.Department" %>
<%@ page import="java.util.List" %>
<%
    Employee emp = (Employee) request.getAttribute("emp");
    List<Department> depList = (List<Department>) request.getAttribute("depList");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 載入 Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 載入 Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <!-- 載入您自訂的 CSS -->
    <link rel="stylesheet" href="./css/style.css">
<title>編輯員工</title>
</head>
<body class="container-fluid">
<div align="center">
<form method="post" action="<%= request.getContextPath() %>/EmployeeServlet">
    <input type="hidden" name="action" value="update" />
    <input type="hidden" name="id" value="<%= emp.getEmpId() %>" />
    姓名：<input type="text" name="name" value="<%= emp.getEmpName() %>"><br>
    信箱：<input type="email" name="email" value="<%= emp.getEmpMail() %>"><br>
    密碼：<input	type="password" name="password" required><br>
    電話：<input type="text" name="phoneNo" value="<%= emp.getEmpPhoneNo() %>"><br>
    部門：
    <select name="depId">
        <% for (Department d : depList) { %>
            <option value="<%= d.getDepId() %>" <%= (emp.getDepartment() != null && emp.getDepartment().getDepId() == d.getDepId()) ? "selected" : "" %>>
                <%= d.getDepName() %>
            </option>
        <% } %>
    </select><br>
    權限：
        		<select name="empACC" required>
            		<option value="admin" <%= "admin".equals(emp.getEmpACC()) ? "selected" : "" %>>管理員</option>
    <option value="staff" <%= "staff".equals(emp.getEmpACC()) ? "selected" : "" %>>一般員工</option>
        		</select><br>
    <input type="submit" value="更新">
</form>
</div>
</body>
</html>