package account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import account.bean.Employee;
import util.DBUtil;

public class EmployeeDAO {
	public Employee login(String email, String password) throws SQLException {
		String sql = "SELECT * FROM EMP WHERE EMPMAIL = ? AND EMPPW = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("EMPID"));
				emp.setEmpName(rs.getString("EMPNAME"));
				emp.setEmpMail(rs.getString("EMPMAIL"));
				return emp;
			}
		}
		return null;
	}

	public List<Employee> getAll() {
		List<Employee> list = new ArrayList<>();
		String sql = "SELECT * FROM EMP";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("EMPID"));
				emp.setEmpName(rs.getString("EMPNAME"));
				emp.setEmpMail(rs.getString("EMPMAIL"));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Employee getById(int id) {
		String sql = "SELECT * FROM EMP WHERE EMPID = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("EMPID"));
				emp.setEmpName(rs.getString("EMPNAME"));
				emp.setEmpMail(rs.getString("EMPMAIL"));
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insert(Employee emp) {
		String sql = "INSERT INTO EMP (EMPNAME, EMPMAIL, EMPPW) VALUES (?, ?, ?)";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, emp.getEmpName());
			ps.setString(2, emp.getEmpMail());
			ps.setString(3, emp.getEmpPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Employee emp) {
		String sql = "UPDATE EMP SET EMPNAME = ?, EMPMAIL = ? WHERE EMPID = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, emp.getEmpName());
			ps.setString(2, emp.getEmpMail());
			ps.setInt(3, emp.getEmpId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM EMP WHERE EMPID = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
