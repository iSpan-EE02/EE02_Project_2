package product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**This class is a collection of DAO method to access EmpBean
 * 
 * 
 * @Author:Anson_Chuang
 * @version 1.0
 * @since 2025-06-17
 */

import product.bean.ProdCateBean;
import product.util.JDBCutil;

public class ProdImagesDao {
	private static final String sqlInsert = "INSERT INTO PRODUCT_CATEGORY (CATE_NAME, PARENT_CATE_ID, CATE_DESC) VALUES(?,?,?)";

	private static final String sqlDelete = "DELETE FROM PRODUCT_CATEGORY WHERE CATE_ID = ?";
	
	private static final String sqlUpdate = "UPDATE product_category\r\n"
			+ "SET CATE_NAME = ?,\r\n"
			+ "CATE_DESC = ?,\r\n"
			+ "PARENT_CATE_ID = ? WHERE CATE_ID = ?";
	
	private static final String sqlQueryAll = 
	"SELECT \r\n"
	+ "    c1.cate_id,\r\n"
	+ "    c1.cate_name,\r\n"
	+ "    c1.cate_desc,\r\n"
	+ "    c1.parent_cate_id,\r\n"
	+ "    CASE \r\n"
	+ "        WHEN \r\n"
	+ "            -- 條件一：是否存在子類別\r\n"
	+ "            EXISTS (\r\n"
	+ "                SELECT 1 \r\n"
	+ "                FROM product_category c2 \r\n"
	+ "                WHERE c2.parent_cate_id = c1.cate_id\r\n"
	+ "            ) \r\n"
	+ "            OR -- 使用 OR 將兩個條件連接起來\r\n"
	+ "            -- 條件二：是否存在旗下產品\r\n"
	+ "            EXISTS (\r\n"
	+ "                SELECT 1 \r\n"
	+ "                FROM product p \r\n"
	+ "                WHERE p.prod_cate_id = c1.cate_id -- 注意：請確認產品表的關聯欄位名稱\r\n"
	+ "            )\r\n"
	+ "        THEN 1 \r\n"
	+ "        ELSE 0 \r\n"
	+ "    END AS is_parent\r\n"
	+ "FROM \r\n"
	+ "    product_category c1\r\n"
	+ "ORDER BY \r\n"
	+ "    c1.cate_id";


	
	
	/*
	 * This is an insert DAO method of ProdCateBean
	 * 
	 */
	public void insertProdCate(ProdCateBean cate) throws SQLException {
		Connection conn = JDBCutil.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		try {
			
			if(cate.getParent_cate_id()==0) {
				cate.setParent_cate_id(null);
			}
			
			Object[] params = {
					cate.getCate_name(),
					cate.getParent_cate_id(),
					cate.getCate_desc()
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
		Connection conn = JDBCutil.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		try {
			queryRunner.update(conn,sqlDelete, cateId);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbUtils.close(conn);
		}
	}

	/*
	 * This is a update DAO method of ProdCateBean
	 * 
	 */
	public void updateProdCate(ProdCateBean cate) throws SQLException {
		Connection conn = JDBCutil.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		try {
			Object[] params = {
					cate.getCate_name(),
					cate.getCate_desc(),
					cate.getParent_cate_id(),
					cate.getCate_id()
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
	 * This is a query All DAO method of ProdCateBean
	 * 
	 */
	public List<ProdCateBean> queryAll() throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		BeanListHandler<ProdCateBean> beanListHandler = new BeanListHandler<>(ProdCateBean.class);
		Connection conn = null;
		try {
			conn = JDBCutil.getConnection();
			return queryRunner.query(conn, sqlQueryAll, beanListHandler);
		}finally {
			DbUtils.closeQuietly(conn);
		}

	}
	
}
