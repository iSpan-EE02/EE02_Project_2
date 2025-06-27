<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動資料</title>
<!-- Bootstrap 5 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div align="center" class="container mt-5">
<%
    List<String> errors = (List<String>) request.getAttribute("errors");
    if (errors == null || errors.isEmpty()) {
%>
    <h2>新增活動</h2>
<%
    } else {
%>
    <h2 style="color:red;">新增失敗，請修正下列錯誤</h2>
<%
    }
%>

<jsp:useBean id="bean" scope="request" class="crowdfund.bean.CampaignBean" />
 <table border="1" class="table table-striped">
        <tr><td>活動名稱</td><td><input type="text" disabled value="${bean.campaignName}"></td></tr>
        <tr><td>活動類別</td><td><input type="text" disabled value="${bean.campaignCategory}"></td></tr>
        <tr><td>目標金額</td><td><input type="text" disabled value="${bean.goalAmount}"></td></tr>
        <tr><td>目前金額</td><td><input type="text" disabled value="${bean.currentAmount}"></td></tr>
        <tr><td>開始日期</td><td><input type="text" disabled value="${bean.startDate}"></td></tr>
        <tr><td>結束日期</td><td><input type="text" disabled value="${bean.endDate}"></td></tr>
        <tr><td>狀態</td><td><input type="text" disabled value="${bean.status}"></td></tr>
        <tr><td>建立者 ID</td><td><input type="text" disabled value="${bean.creatorID}"></td></tr>
        <tr><td>活動描述</td><td><input type="text" disabled value="${bean.description}"></td></tr>
    </table>
</div>
<% 
           
            if (errors != null && !errors.isEmpty()) {
        %>
            <div style="color:red;">
                <ul>
                <% for (String error : errors) { %>
                    <li><%= error %></li>
                <% } %>
                </ul>
            </div>
            <div class="d-flex justify-content-center gap-3 mt-4">
            <form action="<%=request.getContextPath() %>/html/feature/crowdfund/InsertCam.html">
	        <button  class="btn btn-success" type="submit">重新填寫</button>
	        </form>
	        </div>
            
        <% } %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>