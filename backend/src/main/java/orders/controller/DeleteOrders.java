package orders.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orders.dao.OrdersDao;
import orders.utils.LAButil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;


@WebServlet("/DeleteOrders")
public class DeleteOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection conn;
    public DeleteOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ordersId =Integer.parseInt(request.getParameter("orders_id"));
		OrdersDao dao = new OrdersDao();
		dao.deleteOrders(ordersId);
		response.sendRedirect(request.getContextPath()+"/AllOrders");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
