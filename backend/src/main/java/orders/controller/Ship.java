package orders.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orders.bean.ShipBean;
import orders.dao.ShipDao;

import java.io.IOException;
import java.util.List;


@WebServlet("/Ship")
public class Ship extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Ship() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShipDao dao = new ShipDao();
		List<ShipBean> list = dao.findAll();
		
		request.setAttribute("shipList", list);
		request.getRequestDispatcher("/jsp/OrdersInsert.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
