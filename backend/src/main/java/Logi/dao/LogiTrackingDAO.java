package Logi.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;
import Logi.bean.LogiTrackingBean;

public class LogiTrackingDAO {
    private static final String FIND_BY_LOGIID = // 查詢節點
    		"SELECT * FROM Logi_Tracking WHERE logi_id = ? ORDER BY sequence";
    private static final String INSERT_TRACKING = // 新增節點
            "INSERT INTO Logi_Tracking (logi_id, sequence, location_name, status, timestamp) "
          + "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_TRACKING = // 更新節點
    		"UPDATE Logi_Tracking SET location_name = ?, status = ?, timestamp = ? WHERE trak_id = ?";
    private static final String DELETE_TRACKING = // 刪除節點
	        "DELETE FROM Logi_Tracking WHERE trak_id = ?";
	    
    private DataSource ds;

    public LogiTrackingDAO() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/team06db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查詢節點
    public List<LogiTrackingBean> findByLogiId(int logiId) {
        List<LogiTrackingBean> list = new ArrayList<>();
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_LOGIID)) {
            ps.setInt(1, logiId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LogiTrackingBean bean = new LogiTrackingBean();
                bean.setTrakId(rs.getInt("trak_id"));
                bean.setLogiId(rs.getInt("logi_id"));
                bean.setSequence(rs.getInt("sequence"));
                bean.setLocationName(rs.getString("location_name"));
                bean.setStatus(rs.getString("status"));
                bean.setTimestamp(rs.getTimestamp("timestamp"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 新增節點
    public void insertTracking(LogiTrackingBean bean) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_TRACKING)) {
            ps.setInt(1, bean.getLogiId());
            ps.setInt(2, bean.getSequence());
            ps.setString(3, bean.getLocationName());
            ps.setString(4, bean.getStatus());
            ps.setTimestamp(5, bean.getTimestamp());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新節點
    public void updateTracking(LogiTrackingBean bean) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_TRACKING)) {
            ps.setString(1, bean.getLocationName());
            ps.setString(2, bean.getStatus());
            ps.setTimestamp(3, bean.getTimestamp());
            ps.setInt(4, bean.getTrakId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 刪除節點
    public void deleteTracking(int trakId) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_TRACKING)) {
            ps.setInt(1, trakId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
