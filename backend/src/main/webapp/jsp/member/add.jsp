<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="account.bean.*" %>
<%
List<MemberRank> rankList = (List<MemberRank>) request.getAttribute("rankList");
if (rankList == null) {
    out.println("rankList is null!");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增會員</title>
</head>
<body>
<h2>新增會員</h2>
	<form action="<%=request.getContextPath()%>/MemberServlet" method="post">
        <input type="hidden" name="action" value="add">
        姓名：<input type="text" name="memName" required><br>
        性別：<input type="text" name="memGd"><br>
        電話：<input type="text" name="memPn"><br>
        身分證字號：<input type="text" name="memIn"><br>
        地址：<input type="text" name="memAdr"><br>
        信箱：<input type="email" name="memMail" required><br>
        生日：<input type="date" name="memBd"><br>
        密碼：<input type="password" name="memPw" required><br>
        等級：
        <select name="rankId">
            <% for (MemberRank r : rankList) { %>
                <option value="<%=r.getRankId()%>"><%=r.getRankName()%></option>
            <% } %>
        </select><br>
        <input type="submit" value="新增">
    </form>
</body>
</html>