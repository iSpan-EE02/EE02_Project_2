package product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import product.bean.ProdBean;
import product.util.JDBCutil;

/**This class is a collection of DAO method to access ProdBean
 * 有增加，修改，查詢功能，不允許刪除（只能下架，避免造成訂單資訊遺失）
 * 
 * @Author:Anson_Chuang
 * @version 1.0
 * @since 2025-06-17
 */


public class ProdDao {
	private static final String sqlInsert = "INSERT INTO PRODUCT (PROD_NAME, PROD_DESC, PROD_CATE_ID, PROD_STATUS) VALUES(?,?,?,?)";
	
	private static final String sqlUpdate = "UPDATE PRODUCT\r\n"
			+ "SET PROD_NAME = ?,\r\n"
			+ "PROD_DESC = ?,\r\n"
			+ "PROD_CATE_ID = ?,\r\n"
			+ "PROD_STATUS = ?\r\n"
			+ "WHERE PROD_ID = ?";
		
	
	/*
	 * This is an insert DAO method of ProdBean
	 * 
	 */
	public void insertProd(ProdBean prod) throws SQLException {
		Connection conn = JDBCutil.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		try {
			

			Object[] params = {
					prod.getProd_name(),
					prod.getProd_desc(),
					prod.getProd_cate_id(),
					prod.getProd_status()
					
			};
			
			queryRunner.update(conn,sqlInsert, params);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbUtils.close(conn);
		}
		
	}


	/*
	 * This is a update DAO method of ProdBean
	 * 
	 */
	public void updateProd(ProdBean prod) throws SQLException {
		Connection conn = JDBCutil.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		try {
			Object[] params = {
					prod.getProd_name(),
					prod.getProd_desc(),
					prod.getProd_cate_id(),
					prod.getProd_status(),
					prod.getProd_id()
			};
			queryRunner.update(conn,sqlUpdate,params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbUtils.close(conn);
		}
	}


	/*
	 * This is a query All DAO method of ProdBean
	 * 
	 */
	public List<ProdBean> queryAll(ProdBean prodBean) throws SQLException {
		StringBuilder sqlQueryAll = new StringBuilder(			
				"SELECT\r\n"
						+ "    p.PROD_ID,\r\n"
						+ "    p.PROD_NAME,\r\n"
						+ "    p.PROD_DESC,\r\n"
						+ "    p.PROD_CATE_ID,\r\n"
						+ "    p.PROD_STATUS,\r\n"
						+ "    pi.IMAGE_URL\r\n"
						+ "FROM\r\n"
						+ "    PRODUCT AS p\r\n"
						+ "LEFT JOIN\r\n"
						+ "    PRODUCT_IMAGES AS pi ON p.PROD_ID = pi.PROD_ID AND pi.IS_PRIMARY = 1 WHERE 1=1"
				);
		
		QueryRunner queryRunner = new QueryRunner();
		List<Object> params = new ArrayList<>();
		BeanListHandler<ProdBean> beanListHandler = new BeanListHandler<>(ProdBean.class);
		Connection conn = null;
		

		if(prodBean.getProd_name()!=null && !prodBean.getProd_name().isEmpty()) {
			 sqlQueryAll.append(" AND p.prod_name LIKE ?");
	         params.add("%" + prodBean.getProd_name() + "%");
		}
		
		if(prodBean.getProd_status()!=null) {
			 sqlQueryAll.append(" AND p.prod_status = ?");
	         params.add(prodBean.getProd_status());
		}
		
		if(prodBean.getProd_cate_id()!=null) {
			 sqlQueryAll.append(" AND p.prod_cate_id = ?");
	         params.add(prodBean.getProd_cate_id());
		}
		
		
		try {
			conn = JDBCutil.getConnection();
			return queryRunner.query(conn, sqlQueryAll.toString(), beanListHandler, params.toArray());
		}finally {
			DbUtils.closeQuietly(conn);
		}

	}
	
}
