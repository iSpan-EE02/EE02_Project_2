package promotion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class JDBCUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/team06db");
			conn = ds.getConnection();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
////	private static final String DRIVER ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=team06db;encrypt=false";
//	private static final String USER ="shan";
//	private static final String PASSWORD ="0515";
//	
//
//	public static Connection getConnection() throws SQLException{
//		return DriverManager.getConnection(URL,USER,PASSWORD);
//	}
//	
}


