package product.util;

import java.sql.Connection;

public class JNDITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JDBCutil jdbCutil = new JDBCutil();
		Connection connection = jdbCutil.getConnection();
	}

}
