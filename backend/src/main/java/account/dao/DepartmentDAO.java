package account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import account.bean.Department;
import product.util.JDBCutil;


public class DepartmentDAO {

	public List<Department> getAll(){
		List<Department> list = new ArrayList<>();
		String sql = "SELECT * FROM DEP";
		try (Connection conn = JDBCutil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			while (rs.next()) {
				Department dep = new Department();
				dep.setDepId(rs.getInt("DEPID"));
				dep.setDepName(rs.getString("DEPNAME"));
				dep.setMGRId(rs.getInt("MGRID"));
				list.add(dep);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
}
