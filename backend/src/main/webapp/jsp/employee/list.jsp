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

    List<Employee> list = (List<Employee>) request.getAttribute("empList");
    if (list == null) {
        list = new java.util.ArrayList<>();
    }
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>員工列表</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h4 class="text-primary">您好，<%= loginEmp.getEmpName() %>，歡迎回來</h4>
        <div >
            <a href="<%= request.getContextPath() %>/EmployeeServlet?action=toAdd" class="btn btn-success btn-sm">新增員工</a>
            
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle bg-white shadow-sm">
            <thead class="table-primary text-center">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>信箱</th>
                    <th>權限</th>
                    <th>電話</th>
                    <th>部門</th>
                    <th>更新者ID</th>
                    <th>更新時間</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (!list.isEmpty()) {
                        for (Employee emp : list) {
                            Department dep = emp.getDepartment();
                %>
                <tr class="text-center">
                    <td><%= emp.getEmpId() %></td>
                    <td class="text-start"><%= emp.getEmpName() %></td>
                    <td class="text-start"><%= emp.getEmpMail() %></td>
                    <td><%= emp.getEmpACC() %></td>
                    <td><%= emp.getEmpPhoneNo() %></td>
                    <td><%= (dep != null) ? dep.getDepName() : "無部門" %></td>
                    <td><%= emp.getEmpUpdateBy() %></td>
                    <td><%= emp.getEmpUpdateAt() != null ? emp.getEmpUpdateAt() : "" %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/EmployeeServlet?action=edit&id=<%= emp.getEmpId() %>" class="btn btn-sm btn-primary">編輯</a>
                        <a href="<%= request.getContextPath() %>/EmployeeServlet?action=delete&id=<%= emp.getEmpId() %>" class="btn btn-sm btn-danger" onclick="return confirm('確定刪除此員工?');">刪除</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="9" class="text-center">目前沒有員工資料</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>