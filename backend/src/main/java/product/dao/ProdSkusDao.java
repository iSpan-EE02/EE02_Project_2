package product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**This class is a collection of DAO method to access ProdSkusBean
 * 
 * 
 * @Author:Anson_Chuang
 * @version 1.0
 * @since 2025-06-17
 */

import product.bean.ProdSkusBean;
import product.util.JDBCutil;

public class ProdSkusDao {
	private static final String sqlInsert = "INSERT INTO PRODUCT_SKUS(PROD_ID, SKU_CODE, PRICE, STOCK_QUANTITY) VALUES(?,?,?,?)";

	private static final String sqlDelete = "DELETE FROM PRODUCT_SKUS WHERE PROD_ID = ?";
		
	private static final String sqlQueryAll = 
	"SELECT SKU_ID, SKU_CODE, PRICE, STOCK_QUANTITY FROM PRODUCT_SKUS WHERE PROD_ID = ?";


	
	
	/*
	 * This is an insert DAO method of ProdSkusBean		
	 * 
	 */
	public void insertProdSku(ProdSkusBean sku) throws SQLException {
		Connection conn = JDBCutil.getConnection();
		QueryRunner queryRunner = new QueryRunner();
	
		try {
			
			
			Object[] params = {
					sku.getProd_id(),
					sku.getSku_code(),
					sku.getPrice(),
					sku.getStock_quantity()
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
	
	 * This is a delete DAO method of ProdSkusBean
	 * 
	 */
	public void deleteProdSku(Integer prodId) throws SQLException {
		Connection conn = JDBCutil.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		try {
			queryRunner.update(conn,sqlDelete, prodId);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbUtils.close(conn);
		}
	}

	/*
	 * This is a query All DAO method of ProdSkusBean
	 * 
	 */
	
	public List<ProdSkusBean> queryAllByProdID(int prodId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		BeanListHandler<ProdSkusBean> beanListHandler = new BeanListHandler<>(ProdSkusBean.class);
		Connection conn = null;
		try {
			conn = JDBCutil.getConnection();
			return queryRunner.query(conn, sqlQueryAll, beanListHandler, prodId);
		}finally {
			DbUtils.closeQuietly(conn);
		}

	}
	
}
