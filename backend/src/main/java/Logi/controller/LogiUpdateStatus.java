package Logi.controller;

import Logi.bean.LogiTrackingBean;
import Logi.dao.LogiDAO;

import Logi.dao.LogiTrackingDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/LogiUpdateStatus")
public class LogiUpdateStatus extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 導回 LogiTracking.jsp 頁面
        response.sendRedirect(request.getContextPath() + "/jsp/Logi/LogiTracking.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String logiIdStr = request.getParameter("logiId");
        int logiId = Integer.parseInt(logiIdStr);
        LogiTrackingDAO dao = new LogiTrackingDAO();

        // 新增節點（由 insertFlag 判斷）
        if (request.getParameter("insertFlag") != null) {
            int sequence = Integer.parseInt(request.getParameter("sequence"));
            String location = request.getParameter("locationName");
            String status = request.getParameter("status");
            String tsStr = request.getParameter("timestamp");
            if (tsStr.length() == 16) tsStr += ":00"; // 補秒數
            Timestamp timestamp = Timestamp.valueOf(tsStr);

            LogiTrackingBean bean = new LogiTrackingBean(logiId, sequence, location, status, timestamp);
            dao.insertTracking(bean);
            
         // 同步更新物流單實際到達時間
            if ("已送達".equals(status)) {
            	LogiDAO logiDao = new LogiDAO();
                logiDao.updateActualArrival(logiId, timestamp);
            }            
        }

        // 編輯用表單（含更新與刪除）
        String action = request.getParameter("action");

        if ("更新".equals(action)) {
            int trakId = Integer.parseInt(request.getParameter("trakId"));
            String location = request.getParameter("locationName");
            String status = request.getParameter("status");
            String tsStr = request.getParameter("timestamp");
            if (tsStr.length() == 16) tsStr += ":00"; // 補秒數
            Timestamp timestamp = Timestamp.valueOf(tsStr);

            LogiTrackingBean bean = new LogiTrackingBean();
            bean.setTrakId(trakId);
            bean.setLocationName(location);
            bean.setStatus(status);
            bean.setTimestamp(timestamp);

            dao.updateTracking(bean);
        }

        if ("刪除".equals(action)) {
            int trakIdToDelete = Integer.parseInt(request.getParameter("trakId"));
            dao.deleteTracking(trakIdToDelete);
        }

        // 執行完動作後重新導向回查詢
        response.sendRedirect(request.getContextPath() + "/jsp/Logi/LogiTracking.jsp?logiId=" + logiId);
    }
}
