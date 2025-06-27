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

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />

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
            border: 1px solid #000;
            padding: 12px 20px;
            line-height: 1.5;
        }

        .card {
            max-width: 1200px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="container-fluid py-5">
        <div class="row justify-content-center">
            <div class="col-12">
                <div class="card shadow-sm border-0">
                    <div class=".card-body">
                        <h3 class="card-title text-center mb-4">訂單列表</h3>
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
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>