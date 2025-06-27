<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, promotion.bean.promotionBean"%>
<%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Baloo+2&display=swap" rel="stylesheet">
<style>
	body{
		background-color:#fdf5e6;
		font-family: 'UoqMun Then Khung', cursive;		
	}
	.container{
		width:90%;
		margin:0 auto;
	}
	
	body,h2, .actions a, .pagination a {
	  font-family: 'Arial', sans-serif;
	}
	h2{
		text-align:center;
	}
	table{
		table-layout:fixed;
		width:100%;
		border-collapse:collapse;
		margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	}
	table,th,td{
		border: 1px solid #9999CC;
	}
	th{
		background-color:#d4f0ff;
		white-space: nowrap;
	}
	td,th{
		padding:8px;
		text-align:center;
	}
	td{
		 word-break: break-word;
  		 white-space: normal;
 		 max-width: 150px;
 		 vertical-align: top;
 		 overflow: hidden;
  		 text-overflow: ellipsis; 
	}
	td.nowrap{
		white-space: nowrap;
		overflow: hidden;
    	text-overflow: ellipsis; 
  		max-width: 200px;
	}
	
	.ellipsis{
		display: -webkit-box;
		-webkit-line-clamp: 3;  
		-webkit-box-orient: vertical;
		overflow: hidden;
    	text-overflow: ellipsis; 
  		max-width: 200px;
  		height:3em;
  		line-height:1.5em;
  		word-break:break-word;
  		vertical-align:top;
	}
	td img{
		max-width:80px;
		height:auto;
		display:block;
		margin:0 auto;
	}
		
	.pagination{
		text-align:center;
		margin:20px 0;
		fon-size:16px;
	}
	
	.pagination a,
	.pagination span.current-page{
		text-decoration: none;
		background-color: #ffd966;
		padding: 10px 20px;
		border-radius: 6px;
		color: black;
		font-weight: bold;
		margin: 0 5px;
		display: inline-block;
	}
	.pagination a : hover{
		background-color: #ffc107;
	}
	
	.pagination span.current-page{
		background-color: #ffe599;
		cursor: default;
	}
	
	
</style>
<title>活動資訊</title>
</head>
<body>
<div class="container">
<h2>活 動 資 訊</h2>
<% List<promotionBean> promList = (List<promotionBean>) request.getAttribute("promList");
if (promList ==null || promList.isEmpty()){ %>
<p style="text-align:center;">查無資料<P> <%} else{ %>
<% Integer currentPageObj = (Integer) request.getAttribute("currentPage"); 
Integer totalPagesObj = (Integer) request.getAttribute("totalPages"); 
int currentPage = currentPageObj != null ? currentPageObj:1;
int totalPages = totalPagesObj != null ? totalPagesObj:1;%>
<table>
<tr>
<th>活動編號<th>活動名稱<th>活動描述<th>活動類型<th>效期類型<th>使用效期開始<th>使用效期結束<th>領取後有效天數<th>是否啟用<th>建立時間<th>修改時間<th>備註<th>圖片<th>修改<th>刪除
<% List<promotionBean> proms = (ArrayList<promotionBean>)request.getAttribute("proms");for(promotionBean prom : promList) { %>
<tr><td><%= prom.getPromotionId() %></td>
<td><%=prom.getTitle() %></td>
<td>
<div class="ellipsis" title="<%= prom.getDescription() %>"> <%=prom.getDescription() %></div></td>
<td><%=prom.getType() %></td>
<td><%=prom.getValidityType() %></td>
<td>
<div class="ellipsis" title="<%= prom.getValidityFrom() %>"> <%=prom.getValidityFrom() ==null ? "" :prom.getValidityFrom() %></div></td>
<td>
<div class="ellipsis" title="<%= prom.getValidityTo() %>"> <%=prom.getValidityTo() ==null ? "" :prom.getValidityTo() %></div></td>
<td><%=prom.getValidityDays() ==null ? "" :prom.getValidityDays()%></td>
<td><%=prom.getActive() %></td>
<td><%=prom.getCreatedAt() %></td>
<td><%=prom.getUpdatedAt() %></td>
<td>
<div class="ellipsis" title="<%= prom.getNote() %>"> <%=prom.getNote() %></div></td>
<td><img src="<%= request.getContextPath() %>/jsp/promotion/image/<%= prom.getImage() %>" width="80"></td>
<td><a href="PromotionUpdate?promotionId=<%= prom.getPromotionId() %>"><i class="fa-solid fa-pen-to-square"></i></a></td>
<td><a href="PromotionDelete?promotionId=<%= prom.getPromotionId() %>"><i class="fa-solid fa-trash"></i></td></tr>
<% } %>
</table>
<div class="pagination">
<% for (int i =1; i <=totalPages; i++){ %>
<% if (i ==currentPage){ %>
<span class="current-page"><%= i %></span>
<% } else {%>
<a href="PromotionAll?page=<%= i %>"><%= i %></a>
<% } %>
<% } %>
</div>

<% } %>
</div>
</body>
</html>
