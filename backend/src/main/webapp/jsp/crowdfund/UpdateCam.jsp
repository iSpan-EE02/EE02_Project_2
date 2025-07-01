<%@page import="java.util.List"%>
<%@page import="crowdfund.bean.CampaignBean"%>
<%@page import="crowdfund.DAO.FundraisingDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
CampaignBean cam = (CampaignBean) request.getAttribute("cam");

if (cam == null) {
    String campaignID = request.getParameter("campaign_id");
    if (campaignID != null && !campaignID.isEmpty()) {
        try {
            Integer camInteger = Integer.parseInt(campaignID);
            FundraisingDao dao = new FundraisingDao();
            cam = dao.getCamById(camInteger);
        } catch (Exception e) {
            cam = new CampaignBean(); // 若發生錯誤仍要設一個 cam 防止 null
        }
    } else {
        cam = new CampaignBean(); // 沒帶 campaign_id，也給一個空物件避免後續錯誤
    }
}


	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新活動資料</title>
<!-- Bootstrap 5 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div align="center" class="container mt-5">
<h2>更新資料</h2>
<form method="post" action="<%= request.getContextPath() %>/CrowdFundUpdateCam" onsubmit="return validateForm();" enctype="multipart/form-data" >
<table  border="1" class="table table-striped">
<tr>
	<td>活動編號
	<td><input type="text" class="form-control" name = "campaign_id" readonly value="<%= cam.getCampaignID()%>" >
<tr>
	<td>活動名稱<td><input type="text" class="form-control" name="campaign_name" value="<%=cam.getCampaignName()%>" required>
<tr>
	<td>活動類別<td>
	<select name="campaign_category" class="form-select" value="<%=cam.getCampaignCategory()%>">
              	<option value="手作">手作</option>
                <option value="音樂">音樂</option>
                <option value="科技">科技</option>
                <option value="環境">環境</option>
                <option value="社會">社會</option>
                <option value="時尚">時尚</option>
                <option value="教育">教育</option>
                <option value="其他">其他</option>
            </select>
<tr><td>目標金額<td><input type="number" class="form-control" name="goal_amount" value="<%=cam.getGoalAmount()%>" min="1" required>
<tr><td>目前金額<td><input type="number" class="form-control" name="current_amount" value="<%=cam.getCurrentAmount()%>" min="0" required>
<tr><td>開始日期<td><input type="date" class="form-control" name="start_date" id="start_date" value="<%=cam.getStartDate()%>" required>
<tr><td>結束日期<td><input type="date" class="form-control" name="end_date" id="end_date" value="<%=cam.getEndDate()%>" required>
<tr>
   		 	<td>活動圖片</td>
    		<td><input type="file" class="form-control" name="cover_image" accept="image/*" /></td>
    		<input type="hidden" name="original_cover_image" value="<%=cam.getCoverImage()%>">
  		</tr>

<tr><td>活動狀態<td>
	<select name="status" class="form-select" value="<%=cam.getStatus()%>">
              <option value="草稿">草稿</option>
              <option value="進行中">進行中</option>
              <option value="成功">成功</option>
              <option value="已結束">已結束</option>
            </select>
<tr><td>建立者ID<td><input type="text" class="form-control" name="creator_id" value="<%=cam.getCreatorID() %>" required>
<tr><td>描述<td><input type="text" class="form-control" name="description" value="<%=cam.getDescription()%>">
</table>
 <br>
        <input type="submit" class="btn btn-primary" value="確認修改">
        </form>
        
</div>
<% 
            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null && !errors.isEmpty()) {
        %>
            <div style="color:red;">
                <ul>
                <% for (String error : errors) { %>
                    <li><%= error %></li>
                <% } %>
                </ul>
            </div>
	        
	                    
        <% } %>

<script>
function validateForm(){
	  const creatorId = document.querySelector("input[name='creator_id']").value;
	  if (!/^\d+$/.test(creatorId)) {
	      alert("建立者 ID 格式不正確");
	      return false;
	    }
	  return validateDates();
}

  function validateDates(){
	  const start = document.querySelector("#start_date").value;
	  const end = document.querySelector("#end_date").value;
	  if(start && end && new Date(end) < new Date(start)){
		  alert("結束日期不能早於開始日期");
		  return false;
	  }
	  return true;
  }
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>