package crowdfund.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCUtil {
	public static Connection getConnection()  {
		Connection conn = null;
		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/team06db");
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
	        System.out.println("資料庫連線失敗：" + e.getMessage());
	        e.printStackTrace();
	    }
		return conn;
		
	}
	

}
