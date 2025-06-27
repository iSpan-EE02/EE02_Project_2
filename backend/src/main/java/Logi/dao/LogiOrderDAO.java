package Logi.dao;

import java.sql.*;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;

public class LogiOrderDAO {
    private static final String INSERT_LOGI_ORDER =
        "INSERT INTO Logi_Order (logi_id, order_id) VALUES (?, ?)";

    private DataSource ds;

    public LogiOrderDAO() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/team06db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 綁定多筆訂單到物流單
    public void insertLogiOrders(int logiId, List<Integer> orderIds) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_LOGI_ORDER)) {
            for (int orderId : orderIds) {
                ps.setInt(1, logiId);
                ps.setInt(2, orderId);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
