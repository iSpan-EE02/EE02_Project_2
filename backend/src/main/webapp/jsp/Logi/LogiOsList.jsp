<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.text.SimpleDateFormat, Logi.bean.LogiOsBean, Logi.dao.LogiOsDAO" %>
<%
	LogiOsDAO dao = new LogiOsDAO();
	List<LogiOsBean> osList = dao.findAll();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8" />
    <title>訂單列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEJpY4e6pWj44Lr8FZ58E8yBYMkgjC9C5N4wM5zq0ZG5h7x7Q8/ucIgrVskdo"
          crossorigin="anonymous" />

    <style>
		.table-bordered th,
		.table-bordered td,
		.table-bordered {
			border: 1px solid #000;
		}
		
		.table-bordered th,
		.table-bordered td {
			border: 1px solid #000;
			padding: 6px 10px;
		}
	</style>
</head>
<body>
  <div class="container mt-5">
    <h2 class="mb-4">訂單列表</h2>
    <table class="table table-bordered table-sm">
      <thead class="table-light">
        <tr>
          <th>訂單編號</th>
          <th>訂購時間</th>
          <th>收件人姓名</th>
          <th>收件人地址</th>
          <th>收件人電話</th>
          <th>寄件人姓名</th>
          <th>寄件人地址</th>
          <th>建立時間</th>
        </tr>
      </thead>
      <tbody>
        <% if (osList != null && !osList.isEmpty()) {
            for (LogiOsBean os : osList) { %>
        <tr>
          <td><%= os.getOrderId() %></td>
          <td><%= sdf.format(os.getOrderDate()) %></td>
          <td><%= os.getRecName() %></td>
          <td><%= os.getRecAdr() %></td>
          <td><%= os.getRecTel() %></td>
          <td><%= os.getSenName() %></td>
          <td><%= os.getSenAdr() %></td>
          <td><%= sdf.format(os.getCreatedAt()) %></td>
        </tr>
        <% }
          } else { %>
        <tr>
          <td colspan="8" class="text-center">尚無訂單資料</td>
        </tr>
        <% } %>
      </tbody>
    </table>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-pzjw8f+ua7Kw1TIq0Xm8R63e4PK59M8gdbPRFvh+Khkysdjf5IH1ISk5t3dW3jj2"
          crossorigin="anonymous"></script>
</body>
</html>

