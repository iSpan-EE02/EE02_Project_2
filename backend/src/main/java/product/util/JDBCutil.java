package product.util;

/**
 * This class provides functionality for get connection and close resource.
 * 
 * 
 * @author Anson_Chuang
 * @version 1.0
 * @since 2025-06-17
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCutil {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/project2");
			connection = ds.getConnection();
			boolean status = !connection.isClosed();
			System.out.println("連綫狀態："+status);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

		}
		return connection;
	}
	
	
	public static void closeResource(Statement statement) {
		try {
			if(statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

		
	}
	
	public static void closeResource(Statement statement, ResultSet rs) {
		try {
			if(statement != null) {
				statement.close();
			}
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

		
	}
	
	
}
