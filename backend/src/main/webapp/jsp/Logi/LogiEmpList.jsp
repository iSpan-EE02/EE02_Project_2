<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Logi.bean.LogiEmpBean, Logi.dao.LogiEmpDAO" %>
<%
	LogiEmpDAO dao = new LogiEmpDAO();
	List<LogiEmpBean> empList = dao.findAll();
%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>司機列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-KyZXEJpY4e6pWj44Lr8FZ58E8yBYMkgjC9C5N4wM5zq0ZG5h7x7Q8/ucIgrVskdo" 
          crossorigin="anonymous">
    
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
        <h2 class="mb-4">司機列表</h2>
        <table class="table table-bordered table-sm">
            <thead class="table-light">
                <tr>
                    <th>司機編號</th>
                    <th>姓名</th>
                    <th>電話</th>
                </tr>
            </thead>
            <tbody>
                <% if (empList != null && !empList.isEmpty()) {
                	for (LogiEmpBean emp : empList) { %>
                <tr>
                    <td><%= emp.getDriverId() %></td>
                    <td><%= emp.getDriverName() %></td>
                    <td><%= emp.getDriverPhone() %></td>
                </tr>
                <% }} else { %>
                <tr><td colspan="3" class="text-center">尚無資料</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-pzjw8f+ua7Kw1TIq0Xm8R63e4PK59M8gdbPRFvh+Khkysdjf5IH1ISk5t3dW3jj2" 
            crossorigin="anonymous"></script>
</body>
</html>


