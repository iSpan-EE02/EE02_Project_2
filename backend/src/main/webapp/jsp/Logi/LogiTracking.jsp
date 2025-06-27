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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>物流節點管理</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJpY4e6pWj44Lr8FZ58E8yBYMkgjC9C5N4wM5zq0ZG5h7x7Q8/ucIgrVskdo" crossorigin="anonymous">
</head>

<body>
    <div class="container mt-5">
        <h2 class="mb-4">選擇物流單</h2>
        <form method="get" action="LogiTracking.jsp" class="mb-4">
            <div class="row">
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
                <div class="col-md-6 d-flex align-items-end">
                    <button type="submit" class="btn btn-primary">查詢節點</button>
                </div>
            </div>
        </form>

        <h2 class="mb-4">物流單 <%= logiId %> 的節點</h2>
        <table class="table table-bordered">
            <thead>
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
                        <td><button type="submit" name="action" value="更新" class="btn btn-warning">更新</button></td>
                        <td><button type="submit" name="action" value="刪除" class="btn btn-danger">刪除</button></td>
                    </form>
                </tr>
                <% } %>
            </tbody>
        </table>

        <h3>新增節點</h3>
        <form action="<%= request.getContextPath() %>/LogiUpdateStatus" method="post">
            <input type="hidden" name="insertFlag" value="true">
            <input type="hidden" name="logiId" value="<%= logiId %>">
            <table class="table">
                <tr><td><label for="sequence">順序：</label></td>
                	<td><input type="number" name="sequence" class="form-control" required></td></tr>
                <tr><td><label for="locationName">地點：</label></td>
                	<td><input type="text" name="locationName" class="form-control" required></td></tr>
                <tr><td><label for="status">狀態：</label></td>
                	<td><input type="text" name="status" class="form-control" required></td></tr>
                <tr><td><label for="timestamp">時間：</label></td>
                	<td><input type="text" name="timestamp" class="form-control" placeholder="yyyy-MM-dd HH:mm" required></td></tr>
                <tr><td colspan="2"><button type="submit" class="btn btn-success">新增節點</button></td></tr>
            </table>
        </form>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0Xm8R63e4PK59M8gdbPRFvh+Khkysdjf5IH1ISk5t3dW3jj2" crossorigin="anonymous"></script>
</body>
</html>
