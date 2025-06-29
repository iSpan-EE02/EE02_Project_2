package product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**This class is a collection of DAO method to access ProdImagesBean
 * 目前已實現新增和用Prod_ID查詢
 * 
 * @Author:Anson_Chuang
 * @version 1.0
 * @since 2025-06-17
 */


import product.bean.ProdImagesBean;
import product.util.JDBCutil;

public class ProdImagesDao {
	private static final String sqlInsert = "INSERT INTO PRODUCT_IMAGES(PROD_ID, IMAGE_URL, IS_PRIMARY, SORT_ORDER) VALUES(?,?,?,?)";

	private static final String sqlDelete = "DELETE FROM PRODUCT_IMAGES WHERE IMAGE_ID = ?";
	
	private static final String sqlQueryAll = 
	"SELECT IMAGE_ID,IMAGE_URL,IS_PRIMARY,SORT_ORDER FROM PRODUCT_IMAGES WHERE PROD_ID = ?;";
	
	private static final String sqlQueryexist = 
			"SELECT COALESCE(COUNT(*), 0) AS PrimaryImageCount\r\n"
			+ "FROM PRODUCT_IMAGES\r\n"
			+ "WHERE PROD_ID = ? AND IS_PRIMARY = 1;";

	
	
	/*
	 * This is an insert DAO method of ProdImagesBean		
	 * 
	 */
	public void insertProdImage(ProdImagesBean img) throws SQLException {
		Connection conn = JDBCutil.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		try {
			
			
			Object[] params = {
					img.getProd_id(),
					img.getImage_url(),
					img.getIs_primary(),
					img.getSort_order()
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
	
	 * This is a delete DAO method of ProdImagesBean
	 * 
	 */
	public void deleteProdImage(Integer imageId) throws SQLException {
		Connection conn = JDBCutil.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		try {
			queryRunner.update(conn,sqlDelete, imageId);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbUtils.close(conn);
		}
	}


	/*
	 * This is a query All DAO method of ProdImagesBean
	 * 
	 */
	
	public List<ProdImagesBean> queryAllByProdID(int prodId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		BeanListHandler<ProdImagesBean> beanListHandler = new BeanListHandler<>(ProdImagesBean.class);
		Connection conn = null;
		try {
			conn = JDBCutil.getConnection();
			return queryRunner.query(conn, sqlQueryAll, beanListHandler, prodId);
		}finally {
			DbUtils.closeQuietly(conn);
		}

	}
	
	public Integer queryExist(int prodId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		ScalarHandler<Number> scalarHandler = new ScalarHandler<>();
		Connection conn = null;
		try {
			conn = JDBCutil.getConnection();
			Number qn = queryRunner.query(conn, sqlQueryexist,scalarHandler, prodId);
			return qn.intValue();
			
		}finally {
			DbUtils.closeQuietly(conn);
		}

	}
	
}
