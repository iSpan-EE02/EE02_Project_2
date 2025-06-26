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
//	private static final String sqlInsert = "INSERT INTO employee(empno,ename,"
//			+ "hiredate,salary,deptno,title) VALUES(" + "?,?,?,?,?,?)";
//
//	private static final String sqlDelete = "DELETE FROM employee WHERE empno = ?";
//
//	private static final String sqlUpdate = "UPDATE employee " + "SET ename = ?," + "hiredate = ?," + "salary = ?,"
//			+ "deptno = ?," + "title = ?" + "WHERE empno = ?";
	
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

	
	
	
	//
//	private static final String sqlQueryOne = "select * from employee where empno = ?";
//	
//	private static final String sqlQueryAllByPart = "select e.empno,e.ename,e.hiredate,e.salary,e.deptno,e.title,d.dname from employee e Join department d on e.deptno = d.deptno where e.ename Like ? and e.deptno = ?";
//
//	/*
//	 * This is an insert DAO method of EmpBean
//	 * 
//	 */
//	public void insertEmp(EmpBean emp) throws SQLException {
//		Connection conn = JDBCutil.getConnection();
//		PreparedStatement ps = conn.prepareStatement(sqlInsert);
//		ps.setInt(1, Integer.parseInt(emp.getEmpno()));
//		ps.setString(2, emp.getEname());
//		ps.setDate(3, java.sql.Date.valueOf(emp.getHiredate()));
//		ps.setInt(4, Integer.parseInt(emp.getSalary()));
//		ps.setString(5, emp.getDeptno());
//		ps.setString(6, emp.getTitle());
//		ps.execute();
//		JDBCutil.closeResource(ps);
//	}
//
//	/*
//	 * This is a delete DAO method of EmpBean
//	 * 
//	 */
//	public void deleteEmp(String empno) throws SQLException {
//		Connection conn = JDBCutil.getConnection();
//		PreparedStatement stmt = conn.prepareStatement(sqlDelete);
//		stmt.setString(1, empno);
//		stmt.execute();
//		JDBCutil.closeResource(stmt);
//	}
//
//	/*
//	 * This is a update DAO method of EmpBean
//	 * 
//	 */
//	public void updateEmp(EmpBean emp) throws SQLException {
//		Connection conn = JDBCutil.getConnection();
//		PreparedStatement ps = conn.prepareStatement(sqlUpdate);
//		ps.setInt(6, Integer.parseInt(emp.getEmpno()));
//		ps.setString(1, emp.getEname());
//		ps.setDate(2, java.sql.Date.valueOf(emp.getHiredate()));
//		ps.setInt(3, Integer.parseInt(emp.getSalary()));
//		ps.setString(4, emp.getDeptno());
//		ps.setString(5, emp.getTitle());
//		ps.execute();
//		JDBCutil.closeResource(ps);
//	}
//
//	/*
//	 * This is a query single DAO method of EmpBean
//	 * 
//	 */
//	public EmpBean queryOneEmp(String empno) throws SQLException {
//		EmpBean emp = new EmpBean();
//		Connection conn = JDBCutil.getConnection();
//		PreparedStatement stmt = conn.prepareStatement(sqlQueryOne);
//		stmt.setString(1, empno);
//		ResultSet rs = stmt.executeQuery();
//		if (rs.next()) {
//			emp.setEmpno(rs.getString("empno"));
//			emp.setEname(rs.getString("ename"));
//			emp.setHiredate(rs.getString("hiredate"));
//			emp.setSalary(rs.getString("salary"));
//			emp.setDeptno(rs.getString("deptno"));
//			emp.setTitle(rs.getString("title"));
//		}
//		JDBCutil.closeResource(stmt, rs);
//		return emp;
//	}

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
	
//	/*
//	 * This is a query All DAO method of EmpBean by part of name
//	 * 
//	 */
//	public List<EmpBean> queryAllEmpByPart(String enamePart, String deptno) throws SQLException {
//		List<EmpBean> empList = new ArrayList<EmpBean>();
//		// empList.add
//		Connection conn = JDBCutil.getConnection();
//		PreparedStatement stmt = conn.prepareStatement(sqlQueryAllByPart);
//		stmt.setString(1, enamePart + "%");
//		stmt.setInt(2, Integer.parseInt(deptno.trim()));
//		ResultSet rs = stmt.executeQuery();
//		EmpBean emp = null;
//		while (rs.next()) {
//			emp = new EmpBean();
//			emp.setEmpno(rs.getString("empno"));
//			emp.setEname(rs.getString("ename"));
//			emp.setHiredate(rs.getString("hiredate"));
//			emp.setSalary(rs.getString("salary"));
//			emp.setDeptno(rs.getString("deptno"));
//			emp.setTitle(rs.getString("title"));
//			emp.setDname(rs.getString("dname"));
//			empList.add(emp);
//		}
//		JDBCutil.closeResource(stmt, rs);
//		return empList;
//	}

}
