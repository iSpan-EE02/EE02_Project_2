<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
<style>
	body{
		background-color:#fdf5e6;
		font-family:Arial;
	}
	.container{
		width:60%;
		margin:0 auto;
	}
	
	h2{
		text-align:center;
	}
	form{
		padding:20px;
		border-radius:8px;
		box-shadow:0 0 10px rgba(0,0,0,0,1);
	}
	
	.form-group{
		max-width:500px;
		text-align:left;
		margin:10px auto;
	}
	
	.form-group label{
		display:inline-block;
		width:150px;
		font-weight:bold;
	}
	
	.form-group input,
	.form-group select{
		width:100%;
		max-width:250px;
		padding:6px;
		font-size:14px;
	}
	.form-group input[type="file"]{
		padding:0;
	}
	.submit-btn{
		text-align:center;
		margin-top:20px;
	}
	.submit-btn input{
		background-color: #ffd966;
		border: none;
		padding: 10px 30px;
		font-size: 16px;
		font-weight: bold;
		border-radius: 6px;
		cursor: pointer;
	}
	.submit-btn input:hover {
		background-color: #ffc107;
	}
	
</style>
<title>修改活動</title>
</head>
<body>
<div class="container">
<h2>活 動 修 改</h2>
<jsp:useBean id="prom" scope="request" class="promotion.bean.promotionBean" />
<form action="<%= request.getContextPath() %>/PromotionUpdate" method="post" enctype="multipart/form-data">

<div class="form-group">
<label>活動編號</label>
<input type="text" disabled value="<%= prom.getPromotionId() %>">
<input type="hidden" name="promotionId" value="<%= prom.getPromotionId() %>">
</div>  
<div class="form-group">
<label>活動名稱</label>
<input type="text" name="title" value="<%= prom.getTitle() %>">
</div>
<div class="form-group">
<label>活動描述</label>
<input type="text" name="description" value="<%= prom.getDescription() %>">
</div>
<div class="form-group">
<label>活動類型</label>
<select name="type" required>
	<option value="滿額折扣">滿額折扣</option>
	<option value="滿件優惠">滿件優惠</option>
	<option value="折價券">折價券</option>
	<option value="限時優惠">限時優惠</option>
	<option value="免運">免運</option>
	<option value="點數回饋">點數回饋</option>
	<option value="組合活動">組合活動</option>
	</select>
</div>
<div class="form-group">
<label>效期類型</label>
<select name="validityType">
	<option value="FIXED_DATE">固定日期</option>
	<option value="RELATIVE_DAYS">領取後有效天數</option>
	</select>
</div>
<div class="form-group">
<label>使用效期開始</label><input type="text" name="validityFrom" value="<%= prom.getValidityFrom() %>">
</div>
<div class="form-group">
<label>使用效期結束</label><input type="text" name="validityTo" value="<%= prom.getValidityTo() %>">
</div>
<div class="form-group">
<label>領取後有效天數</label><input type="text" name="validityDays" value="<%= prom.getValidityDays() %>">
</div>
<div class="form-group">
<label>是否啟用</label>
<select name="active">
	<option value="1">啟用</option>
	<option value="0">停用</option>
</select>
</div>
<div class="form-group">
<label>備註</label><input type="text" name="note" value="<%= prom.getNote() %>">
</div>
<div class="form-group">
<label>上傳圖片</label><input type="file" name="image">
</div>
<div class="submit-btn">
<input type="submit" value="送出">

</form>
</div>
</body>
</html>
