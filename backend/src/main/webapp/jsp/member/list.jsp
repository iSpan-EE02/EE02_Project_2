<%@ page import="java.util.*" %>
<%@ page import="account.bean.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    List<Member> memList = (List<Member>) request.getAttribute("memList");
    if (memList == null) {
        memList = new java.util.ArrayList<>(); // 避免空指標
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員列表</title>
</head>
<body>
 <h2>會員列表</h2>
    <a href="<%=request.getContextPath()%>/MemberServlet?action=toAdd">新增會員</a> | 
    <a href="<%=request.getContextPath()%>/EmployeeServlet?action=logout">登出</a>
    <br><br>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>性別</th>
            <th>電話</th>
            <th>身分證字號</th>
            <th>地址</th>
            <th>信箱</th>
            <th>生日</th>
            <th>會員等級</th>
            <th>操作</th>
        </tr>
        <%
            for (Member m : memList) {
        %>
        <tr>
            <td><%= m.getMemId() %></td>
            <td><%= m.getMemName() %></td>
            <td><%= m.getMemGd() %></td>
            <td><%= m.getMemPn() %></td>
            <td><%= m.getMemIn() %></td>
            <td><%= m.getMemAdr() %></td>
            <td><%= m.getMemMail() %></td>
            <td><%= m.getMemBd() %></td>
            <td><%= (m.getRank() != null) ? m.getRank().getRankName() : "無" %></td>
            <td>
                <a href="<%=request.getContextPath()%>/MemberServlet?action=edit&id=<%=m.getMemId()%>">編輯</a> | 
                <a href="<%=request.getContextPath()%>/MemberServlet?action=delete&id=<%=m.getMemId()%>" 
                   onclick="return confirm('確定刪除此會員？');">刪除</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>