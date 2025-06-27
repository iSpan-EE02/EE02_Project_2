package Logi.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;
import Logi.bean.LogiBean;

public class LogiDAO {
    private static final String SELECT_ALL_LOGI = // 查詢物流單所有資料
    		"SELECT * FROM Logi";
    private static final String INSERT_LOGI = // 建立物流單
    		"INSERT INTO Logi (driver_id, estimated_arrival, total_fee, total_distance) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_LOGIID = // 根據 logiId 查詢單一物流單
    		"SELECT * FROM Logi WHERE logi_id = ?";
    private static final String UPDATE_LOGI = // 更新物流單
    		"UPDATE Logi SET driver_id = ?, estimated_arrival = ?, total_fee = ?, total_distance = ?, actual_arrival = ? WHERE logi_id = ?";
    private static final String UPDATE_ACTUAL_ARRIVAL = // 更新實際到貨時間
    		"UPDATE Logi SET actual_arrival = ? WHERE logi_id = ?";

    private DataSource ds;

    public LogiDAO() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/team06db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	// 查詢物流單表格中的所有資料
    public List<LogiBean> findAll() {
        List<LogiBean> list = new ArrayList<>();
        String sql = "SELECT * FROM Logi"; 
        try (Connection conn = ds.getConnection();
        		 PreparedStatement ps = conn.prepareStatement(SELECT_ALL_LOGI);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LogiBean logi = new LogiBean();
                logi.setLogiId(rs.getInt("logi_id"));
                logi.setDriverId(rs.getInt("driver_id"));
                logi.setEstimatedArrival(rs.getTimestamp("estimated_arrival"));
                logi.setActualArrival(rs.getTimestamp("actual_arrival"));
                logi.setTotalFee(rs.getBigDecimal("total_fee"));
                logi.setTotalDistance(rs.getBigDecimal("total_distance"));
                logi.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(logi); // 把查到的 LogiBean 加入列表
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list; // 返回查到的所有物流單
    }

    // 建立物流單，回傳 logi_id
    public int insertLogi(LogiBean bean) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_LOGI, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, bean.getDriverId());
            ps.setTimestamp(2, bean.getEstimatedArrival());
            ps.setBigDecimal(3, bean.getTotalFee());
            ps.setBigDecimal(4, bean.getTotalDistance());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    // 根據 logiId 查詢單一物流單
    public List<LogiBean> findByLogiId(int logiId) {
        List<LogiBean> list = new ArrayList<>();
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_LOGIID)) {
            ps.setInt(1, logiId);  // 設定查詢條件
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LogiBean bean = new LogiBean();
                bean.setLogiId(rs.getInt("logi_id"));
                bean.setDriverId(rs.getInt("driver_id"));
                bean.setEstimatedArrival(rs.getTimestamp("estimated_arrival"));
                bean.setActualArrival(rs.getTimestamp("actual_arrival"));
                bean.setTotalFee(rs.getBigDecimal("total_fee"));
                bean.setTotalDistance(rs.getBigDecimal("total_distance"));
                bean.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

	// 更新物流單
    public void updateLogi(LogiBean logi) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_LOGI)) {

            // 設置 SQL 語句的參數
            ps.setInt(1, logi.getDriverId());
            ps.setTimestamp(2, logi.getEstimatedArrival());
            ps.setBigDecimal(3, logi.getTotalFee());
            ps.setBigDecimal(4, logi.getTotalDistance());
            ps.setTimestamp(5, logi.getActualArrival()); // 可能是 null
            ps.setInt(6, logi.getLogiId());

            // 執行更新操作
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 更新實際到貨時間
    public void updateActualArrival(int logiId, Timestamp actualTime) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_ACTUAL_ARRIVAL)) {
            ps.setTimestamp(1, actualTime);
            ps.setInt(2, logiId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
