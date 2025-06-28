<%@ page import="java.util.List" %>
<%@ page import="account.bean.MemberRank" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    List<MemberRank> rankList = (List<MemberRank>) request.getAttribute("rankList");
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>新增會員</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">
    <h4 class="text-primary mb-4">新增會員資料</h4>
    <form action="<%= request.getContextPath() %>/MemberServlet" method="post" class="bg-white p-4 rounded shadow-sm">
        <input type="hidden" name="action" value="add">

        <div class="mb-3">
            <label class="form-label">姓名</label>
            <input type="text" name="memName" required class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">性別</label>
            <select name="memGd" class="form-select" required>
                <option value="">請選擇</option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">電話</label>
            <input type="text" name="memPn" required class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">身分證字號</label>
            <input type="text" name="memIn" required class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">地址</label>
            <input type="text" name="memAdr" required class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">信箱</label>
            <input type="email" name="memMail" required class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">生日</label>
            <input type="date" name="memBd" required class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">會員等級</label>
            <select name="rankId" class="form-select" required>
                <option value="">請選擇</option>
                <% for (MemberRank rank : rankList) { %>
                    <option value="<%= rank.getRankId() %>"><%= rank.getRankName() %></option>
                <% } %>
            </select>
        </div>

        <div class="d-flex justify-content-between">
            <a href="<%= request.getContextPath() %>/MemberServlet?action=list" class="btn btn-secondary">返回列表</a>
            <button type="submit" class="btn btn-success">新增</button>
        </div>
    </form>
</div>
</body>
</html>
