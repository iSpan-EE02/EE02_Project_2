<%@ page import="java.util.List" %>
<%@ page import="account.bean.Employee" %>
<%@ page import="account.bean.Department" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    Employee loginEmp = (Employee) session.getAttribute("emp");
    if (loginEmp == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/employee/login.jsp");
        return;
    }
    Employee emp = (Employee) request.getAttribute("emp");
    List<Department> depList = (List<Department>) request.getAttribute("depList");
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>編輯員工</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">
    <h4 class="text-primary mb-4">編輯員工資料</h4>
    <form action="<%= request.getContextPath() %>/EmployeeServlet" method="post" class="bg-white p-4 rounded shadow-sm">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= emp.getEmpId() %>">

        <div class="mb-3">
            <label for="name" class="form-label">姓名</label>
            <input type="text" id="name" name="name" required class="form-control" value="<%= emp.getEmpName() %>">
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">信箱</label>
            <input type="email" id="email" name="email" required class="form-control" value="<%= emp.getEmpMail() %>">
        </div>

        <div class="mb-3">
            <label for="empACC" class="form-label">帳號/權限</label>
            <input type="text" id="empACC" name="empACC" required class="form-control" value="<%= emp.getEmpACC() %>">
        </div>

        <div class="mb-3">
            <label for="phoneNo" class="form-label">電話</label>
            <input type="number" id="phoneNo" name="phoneNo" required class="form-control" value="<%= emp.getEmpPhoneNo() %>">
        </div>

        <div class="mb-3">
            <label for="depId" class="form-label">部門</label>
            <select id="depId" name="depId" class="form-select" required>
                <option value="">請選擇部門</option>
                <% for (Department dep : depList) { %>
                    <option value="<%= dep.getDepId() %>" <%= (emp.getDepartment() != null && emp.getDepartment().getDepId() == dep.getDepId()) ? "selected" : "" %>>
                        <%= dep.getDepName() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="d-flex justify-content-between">
            <a href="<%= request.getContextPath() %>/EmployeeServlet?action=list" class="btn btn-secondary">返回列表</a>
            <button type="submit" class="btn btn-primary">更新</button>
        </div>
    </form>
</div>

</body>
</html>
