package orders.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import orders.bean.*;
import orders.dao.OrdersDao;
import orders.dao.OrdersItemDao;
import orders.utils.LAButil;

@WebServlet("/GetOneOrders")
public class GetOneOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection conn;
	//查詢單筆訂單
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ordersId =Integer.parseInt(request.getParameter("orders_id")) ;
		
		
//		try {
//			conn= LAButil.getConnection();
//			PreparedStatement stmt = conn.prepareStatement("SELECT o.orders_id, o.mem_id, o.orders_date, s.ship_name, o.total_price, s.ship_fee, o.final_price FROM orders o LEFT JOIN ship s ON o.ship_id = s.ship_id WHERE o.orders_id=?");
//			stmt.setInt(1, ordersId);
//			ResultSet rs = stmt.executeQuery();
//			OrdersBean orders =new OrdersBean();
//			if(rs.next()) {
//				orders.setOrdersId(rs.getInt("orders_id"));
//				orders.setMemId(rs.getInt("mem_id"));
//				orders.setOrdersDate(rs.getTimestamp("orders_date"));
//				orders.setShipName(rs.getString("ship_name"));
//				orders.setShipFee(rs.getInt("ship_fee"));
//				orders.setTotalPrice(rs.getInt("total_price"));
//				orders.setFinalPrice(rs.getInt("final_price"));
//			}
//			request.setAttribute("orders",orders);
//			stmt.close();
//			
//			OrdersDao dao = new OrdersDao(); 
//			List<OrdersItemBean> itemList = dao.findItemsByOrderId(ordersId); 
//			request.setAttribute("itemList", itemList);
//			request.getRequestDispatcher("/jsp/orders/GetOneOrders.jsp").forward(request, response);
		 OrdersDao ordersDao = new OrdersDao();
	        OrdersItemDao itemDao = new OrdersItemDao(); // 你需要自己建立這個 DAO

	        OrdersBean orders = ordersDao.getOneOrders(ordersId);
	        List<OrdersItemBean> itemList = itemDao.getItemsByOrdersId(ordersId);

	        request.setAttribute("orders", orders);
	        request.setAttribute("itemList", itemList);

	        request.getRequestDispatcher("/jsp/orders/GetOneOrders.jsp").forward(request, response);
	        System.out.println("明細數量：" + itemList.size());
	    }
	
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
