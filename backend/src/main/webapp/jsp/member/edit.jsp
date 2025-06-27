<%@ page import="java.util.*" %>
<%@ page import="account.bean.*" %>
<%
    Member member = (Member) request.getAttribute("member");
    List<MemberRank> rankList = (List<MemberRank>) request.getAttribute("rankList");
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
<title>編輯會員</title>
</head>
<body class="container-fluid">
<div align="center">
<h2>編輯會員</h2>
<form action="<%=request.getContextPath()%>/MemberServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="memId" value="<%=member.getMemId()%>">
        姓名：<input type="text" name="memName" value="<%=member.getMemName()%>"><br>
        性別：<input type="text" name="memGd" value="<%=member.getMemGd()%>"><br>
        電話：<input type="text" name="memPn" value="<%=member.getMemPn()%>"><br>
        身分證字號：<input type="text" name="memIn" value="<%=member.getMemIn()%>"><br>
        地址：<input type="text" name="memAdr" value="<%=member.getMemAdr()%>"><br>
        信箱：<input type="email" name="memMail" value="<%=member.getMemMail()%>"><br>
        生日：<input type="date" name="memBd" value="<%=member.getMemBd()%>"><br>
        密碼：<input type="password" name="memPw"><br>
        等級：
        <select name="rankId">
            <% for (MemberRank r : rankList) { %>
                <option value="<%=r.getRankId()%>" <%=r.getRankId() == member.getRank().getRankId() ? "selected" : ""%>>
                    <%=r.getRankName()%>
                </option>
            <% } %>
        </select><br>
        <input type="submit" value="更新">
    </form>
    </div>
</body>
</html>