<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, Logi.dao.LogiDAO, Logi.bean.LogiBean, java.text.SimpleDateFormat, java.sql.SQLException" %>
<%
    // 取得 logiId 的參數，如果有選擇某個 logiId，就篩選出該條物流單
    String logiIdStr = request.getParameter("logiId");
    int logiId = 0; // 預設為顯示所有資料
    System.out.println("接收到的 logiId = " + logiIdStr);
    if (logiIdStr != null && !logiIdStr.isEmpty()) {
        try {
            logiId = Integer.parseInt(logiIdStr); // 將篩選條件轉為 int
        } catch (NumberFormatException e) {
            logiId = 0; // 如果轉換失敗，顯示所有物流單
        }
    }
    
    LogiDAO dao = new LogiDAO();
    List<LogiBean> logiList;
    List<LogiBean> allLogiList = dao.findAll(); // 查詢所有物流單，供下拉選單使用

    // 根據是否選擇 logiId 篩選資料
    if (logiId > 0) {
        logiList = dao.findByLogiId(logiId); // 查詢特定的物流單
    } else {
        logiList = allLogiList; // 查詢所有物流單
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>物流單列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
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
                    <div class="card-body">
                        <h3 class="card-title text-center mb-4">物流單列表</h3>

                        <form method="get" action="LogiList.jsp" class="row g-3 align-items-center mb-4">
                            <div class="col-auto">
                                <label for="logiId" class="col-form-label">選擇物流單：</label>
                            </div>
                            <div class="col-auto">
                                <select name="logiId" id="logiId" class="form-select">
                                    <option value="">全部</option>
                                    <% for (LogiBean bean : allLogiList) { %>
                                    <option value="<%= bean.getLogiId() %>" <%= (bean.getLogiId() == logiId) ? "selected" : "" %>>
                                        <%= bean.getLogiId() %>
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary">查詢</button>
                            </div>
                        </form>

                        <table class="table table-bordered table-sm">
                            <thead class="table-light">        
                                <tr>
                                    <th>物流單 ID</th>
                                    <th>司機 ID</th>
                                    <th>預計到達</th>
                                    <th>實際到達</th>
                                    <th>總金額</th>
                                    <th>總距離</th>
                                    <th>建立時間</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (LogiBean bean : logiList) {
                                %>
                                <tr>
                                    <td><%= bean.getLogiId() %></td>
                                    <td><%= bean.getDriverId() %></td>
                                    <td><%= bean.getEstimatedArrival() != null ? sdf.format(bean.getEstimatedArrival()) : "-" %></td>
                                    <td><%= bean.getActualArrival() != null ? sdf.format(bean.getActualArrival()) : "-" %></td>
                                    <td><%= bean.getTotalFee() %></td>
                                    <td><%= bean.getTotalDistance() %></td>
                                    <td><%= sdf.format(bean.getCreatedAt()) %></td>
                                    <td>
                                        <a href="LogiUpdate.jsp?logiId=<%= bean.getLogiId() %>" class="btn btn-sm btn-primary">修改</a> 
                                    </td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>

                        <div class="mt-4 text-end">
                            <a href="<%= request.getContextPath() %>/jsp/Logi/LogiCreate.jsp" class="btn btn-primary">新增物流單</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>