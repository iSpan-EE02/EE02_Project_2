<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Logi.dao.LogiTrackingDAO, Logi.dao.LogiDAO, Logi.bean.LogiTrackingBean, Logi.bean.LogiBean, java.text.SimpleDateFormat" %>

<%
    LogiDAO logiDAO = new LogiDAO();
    List<LogiBean> allLogiList = logiDAO.findAll(); // 所有物流單，用於下拉選單

    int logiId = 0;
    String logiIdStr = request.getParameter("logiId");
    if (logiIdStr != null && !logiIdStr.isEmpty()) {
        try {
            logiId = Integer.parseInt(logiIdStr);
        } catch (NumberFormatException e) {
            logiId = 0;
        }
    }

    if (logiId == 0 && !allLogiList.isEmpty()) {
        logiId = allLogiList.get(0).getLogiId();
    }

    LogiTrackingDAO trackingDAO = new LogiTrackingDAO();
    List<LogiTrackingBean> trackingList = trackingDAO.findByLogiId(logiId);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>物流節點管理</title>
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

        .table {
            width: 100%;
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
                        <h3 class="card-title text-center mb-4">物流節點管理</h3>
                        
                        <!-- 選擇物流單 -->
                        <form method="get" action="LogiTracking.jsp" class="mb-4">
                            <div class="row align-items-end">
                                <div class="col-md-6">
                                    <label for="logiId" class="form-label">物流單</label>
                                    <select name="logiId" class="form-select">
                                        <% for (LogiBean b : allLogiList) { %>
                                            <option value="<%= b.getLogiId() %>" <%= (b.getLogiId() == logiId ? "selected" : "") %> >
                                                <%= b.getLogiId() %>
                                            </option>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <button type="submit" class="btn btn-primary">查詢節點</button>
                                </div>
                            </div>
                        </form>

                        <!-- 物流節點表格 -->
                        <h4 class="mb-4">物流單 <%= logiId %> 的節點</h4>
                        <table class="table table-bordered">
                            <thead class="table-light">
                                <tr>
                                    <th>順序</th>
                                    <th>地點</th>
                                    <th>狀態</th>
                                    <th>時間</th>
                                    <th colspan="2">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (LogiTrackingBean bean : trackingList) { %>
                                <tr>
                                    <form action="<%= request.getContextPath() %>/LogiUpdateStatus" method="post">
                                        <input type="hidden" name="trakId" value="<%= bean.getTrakId() %>" />
                                        <input type="hidden" name="logiId" value="<%= logiId %>" />
                                        <td><%= bean.getSequence() %></td>
                                        <td><input name="locationName" value="<%= bean.getLocationName() %>" class="form-control" /></td>
                                        <td><input name="status" value="<%= bean.getStatus() %>" class="form-control" /></td>
                                        <td><input name="timestamp" value="<%= sdf.format(bean.getTimestamp()) %>" class="form-control" /></td>
                                        <td><button type="submit" name="action" value="更新" class="btn btn-primary btn-sm">更新</button></td>
                                        <td><button type="submit" name="action" value="刪除" class="btn btn-danger btn-sm">刪除</button></td>
                                    </form>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>

                        <!-- 新增節點表單 -->
                        <h4 class="mt-5 mb-3">新增節點</h4>
                        <form action="<%= request.getContextPath() %>/LogiUpdateStatus" method="post">
                            <input type="hidden" name="insertFlag" value="true">
                            <input type="hidden" name="logiId" value="<%= logiId %>">
                            <table class="table">
                                <tr>
                                    <td><label for="sequence">順序：</label></td>
                                    <td><input type="number" name="sequence" class="form-control" required></td>
                                </tr>
                                <tr>
                                    <td><label for="locationName">地點：</label></td>
                                    <td><input type="text" name="locationName" class="form-control" required></td>
                                </tr>
                                <tr>
                                    <td><label for="status">狀態：</label></td>
                                    <td><input type="text" name="status" class="form-control" placeholder="例:待配送、配送中、已送達" required></td>
                                </tr>
                                <tr>
                                    <td><label for="timestamp">時間：</label></td>
                                    <td><input type="text" name="timestamp" class="form-control" placeholder="yyyy-MM-dd HH:mm" required></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><button type="submit" class="btn btn-primary">新增節點</button></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>