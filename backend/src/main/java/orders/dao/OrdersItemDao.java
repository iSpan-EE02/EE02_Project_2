package orders.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import orders.bean.OrdersItemBean;
import orders.prod.bean.*;
import orders.utils.LAButil;

public class OrdersItemDao {
	
	public OrdersItemDao() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//查詢單筆(指定訂單)
//	public List<OrdersItemBean> findByOrdersId(int ordersId){
//		List<OrdersItemBean> itemList = new ArrayList<>();
//		
//		String sql = "SELECT oi.item_id, oi.orders_id, oi.prod_id, oi.quantity, oi.unit_price, oi.subtotal, p.prod_name, p.prod_img FROM orders_item oi JOIN prod p ON oi.prod_id = p.prod_id WHERE oi.orders_id=?";
//		
//		try {
//			Connection conn = LAButil.getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			
//			ps.setInt(1, ordersId);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				OrdersItemBean item = new OrdersItemBean();
////				item.setItemId(rs.getInt("item_id"));
////				item.setOrdersId(rs.getInt("orders_id"));
//				item.setProdId(rs.getInt("prod_id"));
//				item.setQuantity(rs.getInt("quantity"));
//				item.setUnitPrice(rs.getInt("unit_price"));
//				item.setSubtotal(rs.getInt("subtotal"));
//				
//				item.setProdName(rs.getString("prod_name"));
//				item.setProdImg(rs.getString("prod_img"));
//				
//				itemList.add(item);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return itemList;
//	}
	
	public List<OrdersItemBean> getItemsByOrdersId(int ordersId) {
	    List<OrdersItemBean> itemList = new ArrayList<>();
	    String sql = "SELECT o.orders_id, o.mem_id, p.prod_name, p.prod_img, oi.quantity, oi.unit_price, " +
	                 "oi.quantity * oi.unit_price AS subtotal " +
	                 "FROM orders_item oi " +
	                 "JOIN prod p ON oi.prod_id = p.prod_id " +
	                 "JOIN orders o ON oi.orders_id = o.orders_id " +
	                 "WHERE oi.orders_id = ?";
	    try (Connection conn = LAButil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, ordersId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            OrdersItemBean item = new OrdersItemBean();
	            item.setProdName(rs.getString("prod_name"));
	            item.setProdImg(rs.getString("prod_img"));
	            item.setQuantity(rs.getInt("quantity"));
	            item.setUnitPrice(rs.getInt("unit_price"));
	            item.setSubtotal(rs.getInt("subtotal"));
	            itemList.add(item);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return itemList;
	}
}
