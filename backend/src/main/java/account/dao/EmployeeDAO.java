package account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import account.bean.Department;
import account.bean.Employee;
import product.util.JDBCutil;


public class EmployeeDAO {
	
	public Employee login(String email, String password) throws SQLException {

		String sql = "SELECT e.*, d.DEPID, d.DEPNAME, d.MGRID  FROM EMP e  LEFT JOIN DEP d ON e.DEPID = d.DEPID"
				+ " WHERE e.EMPMAIL = ? AND e.EMPPW = ?";
		try (Connection conn = JDBCutil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("EMPID"));
				emp.setEmpName(rs.getString("EMPNAME"));
				emp.setEmpPhoneNo(rs.getInt("EMPPN"));				
				emp.setEmpMail(rs.getString("EMPMAIL"));
				emp.setEmpDCT(rs.getTimestamp("EMPDCT"));
				emp.setEmpACC(rs.getString("EMPACC"));
				Department department = new Department();
				department.setDepId(rs.getInt("DEPID"));
				department.setDepName(rs.getString("DEPNAME"));
				department.setMGRId(rs.getInt("MGRID"));
				emp.setEmpPassword(rs.getString("EMPPW"));
				emp.setEmpUpdateBy(rs.getInt("EMPUPDATEBY"));
				emp.setEmpUpdateAt(rs.getTimestamp("EMPUPDATEAT"));
				
				emp.setDepartment(department);
				
				return emp;
				
			}
		}
		return null;
	}

	public List<Employee> getAll() {
		List<Employee> list = new ArrayList<>();
		String sql = "SELECT e.*, d.DEPID, d.DEPNAME, d.MGRID  FROM EMP e LEFT JOIN DEP d ON e.DEPID = d.DEPID";
		try (Connection conn = JDBCutil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("EMPID"));
				emp.setEmpName(rs.getString("EMPNAME"));
				emp.setEmpPhoneNo(rs.getInt("EMPPN"));				
				emp.setEmpMail(rs.getString("EMPMAIL"));
				emp.setEmpDCT(rs.getTimestamp("EMPDCT"));
				emp.setEmpACC(rs.getString("EMPACC"));
				Department department = new Department();
				department.setDepId(rs.getInt("DEPID"));
				department.setDepName(rs.getString("DEPNAME"));
				department.setMGRId(rs.getInt("MGRID"));
				emp.setEmpPassword(rs.getString("EMPPW"));
				emp.setEmpUpdateBy(rs.getInt("EMPUPDATEBY"));
				emp.setEmpUpdateAt(rs.getTimestamp("EMPUPDATEAT"));
				emp.setDepartment(department);
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Employee getById(int id) {
		String sql = "SELECT e.*, d.DEPID, d.DEPNAME, d.MGRID FROM EMP e LEFT JOIN DEP d ON e.DEPID = d.DEPID WHERE e.EMPID = ?";
		try (Connection conn = JDBCutil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("EMPID"));
	            emp.setEmpName(rs.getString("EMPNAME"));
	            emp.setEmpPhoneNo(rs.getInt("EMPPN"));
	            emp.setEmpMail(rs.getString("EMPMAIL"));
	            emp.setEmpDCT(rs.getTimestamp("EMPDCT"));
	            emp.setEmpACC(rs.getString("EMPACC"));
	            emp.setEmpPassword(rs.getString("EMPPW"));
	            emp.setEmpUpdateBy(rs.getInt("EMPUPDATEBY"));
	            emp.setEmpUpdateAt(rs.getTimestamp("EMPUPDATEAT"));
	            
	            Department department = new Department();
	            department.setDepId(rs.getInt("DEPID"));
	            department.setDepName(rs.getString("DEPNAME"));
	            department.setMGRId(rs.getInt("MGRID"));
	            emp.setDepartment(department);
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insert(Employee emp) {
		String sql = "INSERT INTO EMP (EMPNAME, EMPPN, EMPMAIL, EMPACC, DEPID, EMPPW, EMPDCT, EMPUPDATEBY, EMPUPDATEAT) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = JDBCutil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			 ps.setString(1, emp.getEmpName());
		        ps.setInt(2, emp.getEmpPhoneNo());
		        ps.setString(3, emp.getEmpMail());
		        ps.setString(4, emp.getEmpACC());
		        ps.setInt(5, emp.getDepartment() != null ? emp.getDepartment().getDepId() : 0); 
		        ps.setString(6, emp.getEmpPassword());
		        ps.setTimestamp(7, emp.getEmpDCT() != null ? new java.sql.Timestamp(emp.getEmpDCT().getTime()) : new java.sql.Timestamp(System.currentTimeMillis()));
		        ps.setInt(8, emp.getEmpUpdateBy());
		        ps.setTimestamp(9, emp.getEmpUpdateAt() != null ? new java.sql.Timestamp(emp.getEmpUpdateAt().getTime()) : null);
		        ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Employee emp) {
		String sql = "UPDATE EMP SET EMPNAME = ?, EMPPN = ?, EMPMAIL = ?, EMPACC = ?, DEPID = ?, EMPPW = ?, EMPUPDATEBY = ?, EMPUPDATEAT = ? WHERE EMPID = ?";
		try (Connection conn = JDBCutil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			 ps.setString(1, emp.getEmpName());
		        ps.setInt(2, emp.getEmpPhoneNo());
		        ps.setString(3, emp.getEmpMail());
		        ps.setString(4, emp.getEmpACC());
		        ps.setInt(5, emp.getDepartment() != null ? emp.getDepartment().getDepId() : 0);
		        ps.setString(6, emp.getEmpPassword());
		        ps.setInt(7, emp.getEmpUpdateBy());
		        ps.setTimestamp(8, emp.getEmpUpdateAt() != null ? new java.sql.Timestamp(emp.getEmpUpdateAt().getTime()) : null);
		        ps.setInt(9, emp.getEmpId());
		        ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM EMP WHERE EMPID = ?";
		try (Connection conn = JDBCutil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
