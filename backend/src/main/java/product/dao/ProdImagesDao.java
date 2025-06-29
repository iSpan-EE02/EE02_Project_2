package product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**This class is a collection of DAO method to access ProdImagesBean
 * 目前已實現新增和用Prod_ID查詢
 * 
 * @Author:Anson_Chuang
 * @version 1.0
 * @since 2025-06-17
 */

import product.bean.ProdCateBean;
import product.bean.ProdImagesBean;
import product.util.JDBCutil;

public class ProdImagesDao {
	private static final String sqlInsert = "INSERT INTO PRODUCT_IMAGES(PROD_ID, IMAGE_URL, IS_PRIMARY, SORT_ORDER) VALUES(?,?,?,?)";

//	private static final String sqlDelete = "DELETE FROM PRODUCT_CATEGORY WHERE CATE_ID = ?";
//	
//	private static final String sqlUpdate = "UPDATE product_category\r\n"
//			+ "SET CATE_NAME = ?,\r\n"
//			+ "CATE_DESC = ?,\r\n"
//			+ "PARENT_CATE_ID = ? WHERE CATE_ID = ?";
//	
	private static final String sqlQueryAll = 
	"SELECT IMAGE_ID,IMAGE_URL,IS_PRIMARY,SORT_ORDER FROM PRODUCT_IMAGES WHERE PROD_ID = ?;";


	
	
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
	 * This is a query All DAO method of ProdCateBean
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
	
}
