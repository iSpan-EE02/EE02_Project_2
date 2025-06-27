package product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import product.bean.ProdBean;

/**This class is a collection of DAO method to access EmpBean
 * 
 * 
 * @Author:Anson_Chuang
 * @version 1.0
 * @since 2025-06-17
 */

import product.bean.ProdCateBean;
import product.util.JDBCutil;

public class ProdDao {
//	private static final String sqlInsert = "INSERT INTO PRODUCT_CATEGORY (CATE_NAME, PARENT_CATE_ID, CATE_DESC) VALUES(?,?,?)";
//
//	private static final String sqlDelete = "DELETE FROM PRODUCT_CATEGORY WHERE CATE_ID = ?";
//	
//	private static final String sqlUpdate = "UPDATE product_category\r\n"
//			+ "SET CATE_NAME = ?,\r\n"
//			+ "CATE_DESC = ?,\r\n"
//			+ "PARENT_CATE_ID = ? WHERE CATE_ID = ?";
	
	private static final String sqlQueryAll = 
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
	+ "    PRODUCT_IMAGES AS pi ON p.PROD_ID = pi.PROD_ID AND pi.IS_PRIMARY = 1";

	
	
	/*
	 * This is an insert DAO method of ProdCateBean
	 * 
	 */
	public void insertProd(ProdCateBean cate) throws SQLException {
//		Connection conn = JDBCutil.getConnection();
//		QueryRunner queryRunner = new QueryRunner();
//		
//		try {
//			
//			if(cate.getParent_cate_id()==0) {
//				cate.setParent_cate_id(null);
//			}
//			
//			Object[] params = {
//					cate.getCate_name(),
//					cate.getParent_cate_id(),
//					cate.getCate_desc()
//			};
//			
//			queryRunner.update(conn,sqlInsert, params);			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			DbUtils.close(conn);
//		}
		
	}

	/*
	
	 * This is a delete DAO method of ProdCateBean
	 * 
	 */
	public void deleteProdCate(Integer cateId) throws SQLException {
//		Connection conn = JDBCutil.getConnection();
//		QueryRunner queryRunner = new QueryRunner();
//		try {
//			queryRunner.update(conn,sqlDelete, cateId);	
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			DbUtils.close(conn);
//		}
	}

	/*
	 * This is a update DAO method of ProdCateBean
	 * 
	 */
	public void updateProdCate(ProdCateBean cate) throws SQLException {
//		Connection conn = JDBCutil.getConnection();
//		QueryRunner queryRunner = new QueryRunner();
//		try {
//			Object[] params = {
//					cate.getCate_name(),
//					cate.getCate_desc(),
//					cate.getParent_cate_id(),
//					cate.getCate_id()
//			};
//			queryRunner.update(conn,sqlUpdate,params);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			DbUtils.close(conn);
//		}
	}


	/*
	 * This is a query All DAO method of ProdBean
	 * 
	 */
	public List<ProdBean> queryAll() throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		BeanListHandler<ProdBean> beanListHandler = new BeanListHandler<>(ProdBean.class);
		Connection conn = null;
		try {
			conn = JDBCutil.getConnection();
			return queryRunner.query(conn, sqlQueryAll, beanListHandler);
		}finally {
			DbUtils.closeQuietly(conn);
		}

	}
	
}
