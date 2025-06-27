package orders.prod.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orders.prod.bean.OrdersProdBean;
import orders.prod.dao.OrdersProdDao;

import java.io.IOException;
import java.util.List;


@WebServlet("/OrdersGetAllProd")
public class OrdersGetAllProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public OrdersGetAllProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrdersProdDao dao = new OrdersProdDao();
		List<OrdersProdBean> prodList = dao.findAll();
		
		request.setAttribute("OrdersProdList", prodList);
		request.getRequestDispatcher("/jsp/orders/orders.prod/OrdersProdList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
