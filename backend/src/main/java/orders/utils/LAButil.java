package orders.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.valves.rewrite.Substitution.StaticElement;

public class LAButil {
//	private static final String URL="jdbc:sqlserver://localhost:1433;DatabaseName=team06db;encrypt=false";
//	private static final String USER="yulin";
//	private static final String PASSWORD="19970319";
//	
//	public static Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(URL, USER, PASSWORD);
//	}
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