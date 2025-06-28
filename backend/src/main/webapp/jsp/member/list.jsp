<%@ page import="java.util.List" %>
<%@ page import="account.bean.Member" %>
<%@ page import="account.bean.MemberRank" %>
<%@ page import="account.bean.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    Employee loginEmp = (Employee) session.getAttribute("emp");
    if (loginEmp == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/employee/login.jsp");
        return;
    }

    List<Member> memList = (List<Member>) request.getAttribute("memList");
    if (memList == null) {
        memList = new java.util.ArrayList<>(); // 避免 null 錯誤
    }
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>會員列表</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h4 class="text-primary">
            您好，<%= loginEmp.getEmpName() %>，歡迎回來
        </h4>
        <div >
            <a href="<%= request.getContextPath() %>/MemberServlet?action=toAdd" class="btn btn-success btn-sm">新增會員</a>
          
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle bg-white shadow-sm">
            <thead class="table-primary text-center">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>性別</th>
                    <th>電話</th>
                    <th>身分證</th>
                    <th>地址</th>
                    <th>信箱</th>
                    <th>生日</th>
                    <th>會員等級</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <%
                if (!memList.isEmpty()) {
                    for (Member m : memList) {
                        MemberRank rank = m.getRank();
            %>
                <tr class="text-center">
                    <td><%= m.getMemId() %></td>
                    <td class="text-start"><%= m.getMemName() %></td>
                    <td><%= m.getMemGd() %></td>
                    <td><%= m.getMemPn() %></td>
                    <td><%= m.getMemIn() %></td>
                    <td class="text-start"><%= m.getMemAdr() %></td>
                    <td class="text-start"><%= m.getMemMail() %></td>
                    <td><%= m.getMemBd() %></td>
                    <td><%= (rank != null) ? rank.getRankName() : "無" %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/MemberServlet?action=edit&id=<%= m.getMemId() %>" class="btn btn-sm btn-primary">編輯</a>
                        <a href="<%= request.getContextPath() %>/MemberServlet?action=delete&id=<%= m.getMemId() %>" class="btn btn-sm btn-danger" onclick="return confirm('確定刪除此會員？');">刪除</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="10" class="text-center">目前沒有會員資料</td>
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
