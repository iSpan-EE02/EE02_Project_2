package Logi.dao;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;
import Logi.bean.LogiEmpBean;

public class LogiEmpDAO {
    private static final String SELECT_ALL_EMP = "SELECT * FROM Logi_EMP";

    private DataSource ds;

    public LogiEmpDAO() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/team06db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查詢所有司機
    public List<LogiEmpBean> findAll() {
        List<LogiEmpBean> list = new ArrayList<>();
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_EMP);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LogiEmpBean bean = new LogiEmpBean();
                bean.setDriverId(rs.getInt("driver_id"));
                bean.setDriverName(rs.getString("driver_name"));
                bean.setDriverPhone(rs.getString("driver_phone"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}