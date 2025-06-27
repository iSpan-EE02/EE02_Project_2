package Logi.controller;

import Logi.bean.*;
import Logi.dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.math.BigDecimal;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet("/LogiCreate")

public class LogiCreate extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogiOsDAO osDao = new LogiOsDAO();
        List<LogiOsBean> orders = osDao.findAll();  // 查詢所有訂單
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("LogiCreate.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 從表單中獲取數據
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        // 將 datetime-local 格式轉為 Timestamp
        String estimatedStr = request.getParameter("estimatedArrival"); // e.g. "2025-06-27T14:30"
        LocalDateTime estimatedLdt = LocalDateTime.parse(estimatedStr); // 使用 Java 8 的時間 API
        Timestamp estimatedArrival = Timestamp.valueOf(estimatedLdt);

        BigDecimal totalFee = new BigDecimal(request.getParameter("totalFee"));
        BigDecimal totalDistance = new BigDecimal(request.getParameter("totalDistance"));
        
        // 處理手動輸入的訂單 ID
        String orderIdsStr = request.getParameter("orderIds");
        String[] orderIdsArr = orderIdsStr.split(",");  // 以逗號分隔
        List<Integer> orderIds = new ArrayList<>();

        // 轉換成整數
        for (String id : orderIdsArr) {
            orderIds.add(Integer.parseInt(id.trim()));  // 去除空白並轉換為 Integer
        }

        // 創建 LogiBean 來新增物流單
        LogiBean logiBean = new LogiBean(driverId, estimatedArrival, totalFee, totalDistance);
        LogiDAO logiDAO = new LogiDAO();
        int logiId = logiDAO.insertLogi(logiBean);  // 插入物流單並獲取 logiId

        // 把物流單與訂單關聯
        LogiOrderDAO logiOrderDAO = new LogiOrderDAO();
        logiOrderDAO.insertLogiOrders(logiId, orderIds);

        // 重定向到物流單列表頁或成功頁面
        response.sendRedirect(request.getContextPath() + "/jsp/Logi/LogiList.jsp");
    }
}