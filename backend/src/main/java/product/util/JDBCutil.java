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
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCutil {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/team06db");
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
	
	
	
	
	
}
