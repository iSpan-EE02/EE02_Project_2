<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>建立物流單</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Bootstrap 5 -->
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
    </style>
</head>

<body>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <h3 class="card-title text-center mb-4">建立物流單</h3>

                        <form action="${pageContext.request.contextPath}/LogiCreate" method="post">
                            <div class="mb-3">
                                <label for="driverId" class="form-label">司機 ID：</label>
                                <input type="number" class="form-control" name="driverId" id="driverId" placeholder="例: 101, 102, 103" required>
                            </div>

                            <div class="mb-3">
                                <label for="estimatedArrival" class="form-label">預計到貨時間：</label>
                                <input type="datetime-local" class="form-control" name="estimatedArrival" id="estimatedArrival" required>
                            </div>

                            <div class="mb-3">
                                <label for="totalFee" class="form-label">總金額：</label>
                                <input type="number" class="form-control" name="totalFee" id="totalFee" step="0.01" required>
                            </div>

                            <div class="mb-3">
                                <label for="totalDistance" class="form-label">總距離 (km)：</label>
                                <input type="number" class="form-control" name="totalDistance" id="totalDistance" step="0.01" required>
                            </div>

                            <div class="mb-3">
                                <label for="orderIds" class="form-label">訂單 ID（逗號分隔）：</label>
                                <input type="text" class="form-control" name="orderIds" id="orderIds" placeholder="例: 1001, 1002, 1003" required>
                            </div>

                            <div class="text-end">
                                <button type="submit" class="btn btn-primary">提交</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
