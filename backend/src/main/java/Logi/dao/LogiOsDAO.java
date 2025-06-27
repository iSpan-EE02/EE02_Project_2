package Logi.dao;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;
import Logi.bean.LogiOsBean;

public class LogiOsDAO {
	private static final String SELECT_ALL_OS = "SELECT * FROM Logi_Os";
	 
    private DataSource ds;

    public LogiOsDAO() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/team06db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查詢所有訂單
    public List<LogiOsBean> findAll() {
        List<LogiOsBean> list = new ArrayList<>();
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_OS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LogiOsBean bean = new LogiOsBean();
                bean.setOrderId(rs.getInt("order_id"));
                bean.setOrderDate(rs.getTimestamp("order_date"));
                bean.setRecName(rs.getString("rec_name"));
                bean.setRecAdr(rs.getString("rec_adr"));
                bean.setRecTel(rs.getString("rec_tel"));
                bean.setSenName(rs.getString("sen_name"));
                bean.setSenAdr(rs.getString("sen_adr"));
                bean.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
