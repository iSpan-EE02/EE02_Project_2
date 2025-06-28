<%@ page import="account.bean.Member" %>
<%@ page import="account.bean.MemberRank" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%
    Member mem = (Member) request.getAttribute("member");
    List<MemberRank> rankList = (List<MemberRank>) request.getAttribute("rankList");
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>編輯會員</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">

    <% if (mem == null) { %>
        <div class="alert alert-danger">
            找不到會員資料，請從會員列表重新選擇。
        </div>
        <a href="<%= request.getContextPath() %>/MemberServlet?action=list" class="btn btn-secondary">返回列表</a>
    <% } else { %>

    <h4 class="text-primary mb-4">編輯會員資料</h4>
    <form action="<%= request.getContextPath() %>/MemberServlet" method="post" class="bg-white p-4 rounded shadow-sm">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="memId" value="<%= mem.getMemId() %>">

        <!-- 表單欄位 -->
        <div class="mb-3">
            <label class="form-label">姓名</label>
            <input type="text" name="memName" value="<%= mem.getMemName() %>" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">性別</label>
            <select name="memGd" class="form-select" required>
                <option value="男" <%= "男".equals(mem.getMemGd()) ? "selected" : "" %>>男</option>
                <option value="女" <%= "女".equals(mem.getMemGd()) ? "selected" : "" %>>女</option>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">電話</label>
            <input type="text" name="memPn" value="<%= mem.getMemPn() %>" class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">身分證字號</label>
            <input type="text" name="memIn" value="<%= mem.getMemIn() %>" class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">地址</label>
            <input type="text" name="memAdr" value="<%= mem.getMemAdr() %>" class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">信箱</label>
            <input type="email" name="memMail" value="<%= mem.getMemMail() %>" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">生日</label>
            <input type="date" name="memBd" value="<%= mem.getMemBd() %>" class="form-control">
        </div>

        <div class="mb-3">
            <label class="form-label">會員等級</label>
            <select name="rankId" class="form-select">
                <% for (MemberRank r : rankList) { %>
                    <option value="<%= r.getRankId() %>" <%= r.getRankId() == mem.getRank().getRankId() ? "selected" : "" %>>
                        <%= r.getRankName() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">密碼</label>
            <input type="password" name="memPw" value="<%= mem.getMemPw() %>" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">贈送給會員ID</label>
            <input type="number" name="giftToUserId" value="<%= mem.getGiftToUser() != null ? mem.getGiftToUser().getMemId() : "" %>" class="form-control">
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">儲存修改</button>
            <a href="<%= request.getContextPath() %>/MemberServlet?action=list" class="btn btn-secondary">取消</a>
        </div>
    </form>

    <% } %>

</div>

</body>
</html>
