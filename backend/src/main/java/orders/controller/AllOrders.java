package orders.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orders.bean.OrdersBean;
//import orders.bean.Products;
import orders.dao.OrdersDao;

import java.io.IOException;
import java.util.List;


@WebServlet("/AllOrders")
public class AllOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AllOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrdersDao ordersDao = new OrdersDao();
		List<OrdersBean> ordersList = ordersDao.findAll();
//		System.out.println("✅ 查到訂單筆數：" + ordersList.size());
	    request.setAttribute("ordersList", ordersList);
	    request.getRequestDispatcher("/jsp/orders/Orders.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.sendRedirect(request.getContextPath() + "/AllOrders");
		
	}

}
