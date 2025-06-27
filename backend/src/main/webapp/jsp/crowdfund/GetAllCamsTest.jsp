<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,crowdfund.bean.CampaignBean"%>
    <%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>活動資料</title>
<!-- Bootstrap 5 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div align="center">
<h2>活動資料</h2>
<table border="1"  class="table table-striped">
<tr style="background-color:#a8fefa">
<th>活動編號</th>
            <th>活動名稱</th>
            <th>活動類別</th>
            <th>目標金額</th>
            <th>目前金額</th>
            <th>開始日期</th>
            <th>結束日期</th>
            <th>活動狀態</th>
            <th>建立者 ID</th>
            <th>描述</th>
            <th>操作</th>
        </tr>



<% List<CampaignBean> cams =(ArrayList<CampaignBean>)request.getAttribute("cams");
for(CampaignBean cam : cams){%>
			<tr><td><%= cam.getCampaignID() %></td>
            <td><%= cam.getCampaignName() %></td>
            <td><%= cam.getCampaignCategory() %></td>
            <td><%= cam.getGoalAmount() %></td>
            <td><%= cam.getCurrentAmount() %></td>
            <td><%= cam.getStartDate() %></td>
            <td><%= cam.getEndDate() %></td>
            <td><%= cam.getStatus() %></td>
            <td><%= cam.getCreatorID() %></td>
            <td><%= cam.getDescription() %></td>
            <td>
            <div style="display:flex;">
    <form action="<%= request.getContextPath() %>/jsp/crowdfund/UpdateCam.jsp" method="get" style="display:inline-block;">
        <input type="hidden" name="campaign_id" value="<%= cam.getCampaignID() %>">
        <button type="submit"><i class="fa-solid fa-pen-to-square"></i> 修改</button>
    </form>
    
    <form action="<%= request.getContextPath() %>/CrowdFundDeleteCam" method="post" style="display:inline-block;"
    onsubmit="return confirm('確定要刪除這個募資活動嗎?\n活動編號:<%=cam.getCampaignID() %>\n名稱:<%=cam.getCampaignName() %>');">
        <input type="hidden" name="campaign_id" value="<%= cam.getCampaignID() %>">
        <button type="submit"><i class="fa-solid fa-trash"></i> 刪除</button>
    </form>
    </div>
</td>
            
	
<%} %>


</table>
<h3>共<%= cams.size()%>筆活動資料</h3>
<br>
<div class="d-flex justify-content-center gap-3 mt-4">
	<form action="<%= request.getContextPath() %>/jsp/crowdfund/IndexFund.jsp">
	<button  class="btn btn-success" type="submit">返回募資活動系統</button>
	</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>