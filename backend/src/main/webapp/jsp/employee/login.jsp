<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">

<head>
    <meta charset="UTF-8">
    <title>後台入口</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card p-5 shadow" style="max-width: 400px; width: 100%;">
            <h3 class="text-center mb-4 text-primary">請選擇管理系統</h3>

            <div class="d-grid gap-3">
                <a href="<%=request.getContextPath()%>/MemberServlet?action=list" class="btn btn-outline-primary btn-lg">
                    會員管理系統
                </a>
                <a href="<%=request.getContextPath()%>/EmployeeServlet?action=list" class="btn btn-outline-success btn-lg">
                    員工管理系統
                </a>
            </div>
        </div>
    </div>
</body>

</html>
