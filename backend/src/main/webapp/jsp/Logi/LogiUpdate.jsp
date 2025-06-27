<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="Logi.dao.LogiDAO, Logi.bean.LogiBean, java.text.SimpleDateFormat" %>
<%
    // 取得 logiId 的參數，如果有選擇某個 logiId，就篩選出該條物流單
    String logiIdStr = request.getParameter("logiId");
    LogiDAO dao = new LogiDAO();  // 初始化 DAO
    LogiBean logi = null;

    if (logiIdStr != null) {
        int logiId = Integer.parseInt(logiIdStr);
        logi = dao.findByLogiId(logiId).get(0); // 查找該物流單
    }

    // 用來格式化日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // 處理表單提交
    if (request.getMethod().equalsIgnoreCase("POST")) {
        String driverIdStr = request.getParameter("driverId");
        String estimatedArrivalStr = request.getParameter("estimatedArrival");
        String totalFeeStr = request.getParameter("totalFee");
        String totalDistanceStr = request.getParameter("totalDistance");
        String actualArrivalStr = request.getParameter("actualArrival");

        // 設定並更新物流單資料
        if (logi != null) {
            logi.setDriverId(Integer.parseInt(driverIdStr));
            logi.setEstimatedArrival(new java.sql.Timestamp(sdf.parse(estimatedArrivalStr).getTime()));
            logi.setTotalFee(new java.math.BigDecimal(totalFeeStr));
            logi.setTotalDistance(new java.math.BigDecimal(totalDistanceStr));
            if (actualArrivalStr != null && !actualArrivalStr.isEmpty()) {
                logi.setActualArrival(new java.sql.Timestamp(sdf.parse(actualArrivalStr).getTime()));
            }

            dao.updateLogi(logi); // 更新物流單
            logi = dao.findByLogiId(logi.getLogiId()).get(0); // 重新查詢以顯示更新後的資料
        }
    }
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>更新物流單</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEJpY4e6pWj44Lr8FZ58E8yBYMkgjC9C5N4wM5zq0ZG5h7x7Q8/ucIgrVskdo"
          crossorigin="anonymous" />
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">更新物流單</h2>

    <form method="post" novalidate>
        <input type="hidden" name="logiId" value="<%= logi != null ? logi.getLogiId() : "" %>">
		
		<div class="mb-3">
        <label for="driverId" class="form-label">司機 ID：</label>
        <input type="text" id="driverId" name="driverId" class="form-control" 
        	value="<%= logi != null ? logi.getDriverId() : "" %>" required><br><br>
		</div>
		
		<div class="mb-3">
        <label for="estimatedArrival" class="form-label">預計到達時間：</label>
        <input type="text" id="estimatedArrival" name="estimatedArrival"  class="form-control" 
               value="<%= logi != null ? sdf.format(logi.getEstimatedArrival()) : "" %>" required><br><br>
		</div>
		
		<div class="mb-3">
        <label for="totalFee" class="form-label">總金額：</label>
        <input type="text" id="totalFee" name="totalFee" class="form-control"
        	 value="<%= logi != null ? logi.getTotalFee() : "" %>" required><br><br>
		</div>
		
		<div class="mb-3">
        <label for="totalDistance" class="form-label">總距離：</label>
        <input type="text" id="totalDistance" name="totalDistance" class="form-control"
        	 value="<%= logi != null ? logi.getTotalDistance() : "" %>" required><br><br>
		</div>
		
		<div class="mb-3">
			<label for="actualArrival" class="form-label">實際到達時間：</label>
        	<input type="text" id="actualArrival" name="actualArrival" class="form-control" 
     		  value="<%= logi != null && logi.getActualArrival() != null ? sdf.format(logi.getActualArrival()) : "" %>"><br><br>
		</div>
		<br><br>
        <button type="submit" class="btn btn-primary">更新</button>
        <br><br>
        <a href="<%= request.getContextPath() %>/jsp/Logi/LogiList.jsp" class="btn btn-secondary ms-2">返回物流單列表</a>
    </form>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pzjw8f+ua7Kw1TIq0Xm8R63e4PK59M8gdbPRFvh+Khkysdjf5IH1ISk5t3dW3jj2"
        crossorigin="anonymous"></script>
</body>
</html>



