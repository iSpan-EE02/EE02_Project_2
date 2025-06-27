package orders.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orders.bean.OrdersBean;
import orders.bean.OrdersItemBean;
import orders.bean.ShipBean;
import orders.dao.OrdersDao;
import orders.dao.ShipDao;
import orders.prod.bean.OrdersProdBean;
import orders.prod.dao.OrdersProdDao;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


@WebServlet("/InsertOrders")
public class InsertOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShipDao dao = new ShipDao();
		List<ShipBean> shipList = dao.findAll();
		request.setAttribute("shipList", shipList);
		request.getRequestDispatcher("/jsp/orders/OrdersInsert.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		try {
		int memId = 1;
		int shipId = Integer.parseInt(request.getParameter("ship_id"));
		int shipFee = Integer.parseInt(request.getParameter("ship_fee"));
		int totalPrice = Integer.parseInt(request.getParameter("total_price"));
		int finalPrice = Integer.parseInt(request.getParameter("final_price"));
		
		OrdersBean bean = new OrdersBean();
		bean.setMemId(1);// 假裝這是登入會員的 ID(先寫死,模擬用戶)
		bean.setShipId(shipId);
		bean.setShipFee(shipFee);
		bean.setFinalPrice(finalPrice);
		bean.setTotalPrice(totalPrice);

		OrdersDao dao = new OrdersDao();
		int ordersId = dao.insertOrders(bean);
		
//		response.sendRedirect(request.getContextPath()+"/AllOrders");
		List<OrdersBean> ordersList = dao.findAll();
		request.setAttribute("ordersList", ordersList);
		response.sendRedirect(request.getContextPath() + "/AllOrders"); 
	}catch(Exception e){
		e.printStackTrace();
		response.setContentType("text/html;charset = UTF-8");
		response.getWriter().println("新增失敗:"+e.getMessage());
	}
		System.out.println("✅ 成功新增訂單一筆！");		
}

}
