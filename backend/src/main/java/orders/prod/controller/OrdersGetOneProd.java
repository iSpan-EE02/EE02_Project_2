package orders.prod.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orders.prod.bean.OrdersProdBean;
import orders.prod.dao.OrdersProdDao;
import orders.utils.LAButil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/GetOneProd")
public class OrdersGetOneProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public OrdersGetOneProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int prodId = Integer.parseInt(request.getParameter("prod_id"));
			
			OrdersProdDao dao = new OrdersProdDao();
			OrdersProdBean prod = dao.findById(prodId);
			
			request.setAttribute("prod", prod);
			request.getRequestDispatcher("/jsp/orders/OrdersProdDetail.jsp");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "查詢商品失敗");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
