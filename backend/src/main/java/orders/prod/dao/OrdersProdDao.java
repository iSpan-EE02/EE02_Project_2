package orders.prod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import orders.prod.bean.OrdersProdBean;
import orders.utils.LAButil;

public class OrdersProdDao {
	
	public OrdersProdDao(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//查詢全部
	public List<OrdersProdBean> findAll(){
		List<OrdersProdBean> list = new ArrayList<OrdersProdBean>();
		String getAllSql="SELECT * FROM prod ";
		
		try {
			Connection conn = LAButil.getConnection();
			PreparedStatement ps = conn.prepareStatement(getAllSql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				OrdersProdBean bean = new OrdersProdBean();
				bean.setProdId(rs.getInt("prod_id"));
				bean.setProdName(rs.getString("prod_name"));
				bean.setProdImg(rs.getString("prod_img"));
				bean.setProdPrice(rs.getInt("prod_price"));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	//查詢單筆(依prod_id)
	public OrdersProdBean findById(int prodId) {
		String getOneProd="SELECT prod_id,prod_name,prod_ing,prod_price FROM prod WHERE prod_id=?";
		OrdersProdBean bean = null;
		try(Connection conn = LAButil.getConnection();
		PreparedStatement ps = conn.prepareStatement(getOneProd)){
			ps.setInt(1, prodId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				bean = new OrdersProdBean();
				bean.setProdId(rs.getInt("prod_id"));
				bean.setProdName(rs.getString("prod_name"));
				bean.setProdImg(rs.getString("prod_img"));
				bean.setProdPrice(rs.getInt("prod_price"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		return bean;
	}
}
