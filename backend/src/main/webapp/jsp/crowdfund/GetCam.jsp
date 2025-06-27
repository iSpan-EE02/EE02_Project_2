<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工資料</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>員工資料</h2>
<jsp:useBean id="cam" scope="request" class="crowdfund.bean.CampaignBean" />
<table>
<tr><td>活動編號</td>
        <td><input type="text" disabled value="<%= cam.getCampaignID() %>"></td></tr>
    <tr><td>活動名稱</td>
        <td><input type="text" disabled value="<%= cam.getCampaignName() %>"></td></tr>
    <tr><td>活動類別</td>
        <td><input type="text" disabled value="<%= cam.getCampaignCategory() %>"></td></tr>
    <tr><td>目標金額</td>
        <td><input type="text" disabled value="<%= cam.getGoalAmount() %>"></td></tr>
    <tr><td>目前金額</td>
        <td><input type="text" disabled value="<%= cam.getCurrentAmount() %>"></td></tr>
    <tr><td>開始日期</td>
        <td><input type="text" disabled value="<%= cam.getStartDate() %>"></td></tr>
    <tr><td>結束日期</td>
        <td><input type="text" disabled value="<%= cam.getEndDate() %>"></td></tr>
    <tr><td>活動狀態</td>
        <td><input type="text" disabled value="<%= cam.getStatus() %>"></td></tr>
    <tr><td>建立者 ID</td>
        <td><input type="text" disabled value="<%= cam.getCreatorID() %>"></td></tr>
    <tr><td>活動描述</td>
        <td><input type="text" disabled value="<%= cam.getDescription() %>"></td></tr>
</table>
</div>

</body>
</html>