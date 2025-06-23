package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName= team06db";
	    private static final String USER = "eric";
	    private static final String PASSWORD = "123456";

	    static {
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException("找不到 SQL Server 驅動", e);
	        }
	    }

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	}

