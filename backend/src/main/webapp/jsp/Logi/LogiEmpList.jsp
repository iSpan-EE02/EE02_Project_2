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

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Microsoft JhengHei', sans-serif;
            background-color: #f4f6f9;
        }

        .card a {
            text-decoration: none;
            font-weight: 500;
        }

        .card a:hover {
            text-decoration: underline;
        }

        .table-bordered th,
        .table-bordered td,
        .table-bordered {
            border: 1px solid #000;
        }

        .table-bordered th,
        .table-bordered td {
            padding: 8px 12px;
        }
    </style>
</head>

<body>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <h3 class="card-title text-center mb-4">司機列表</h3>
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
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>