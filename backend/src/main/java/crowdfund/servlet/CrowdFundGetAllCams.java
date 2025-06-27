package crowdfund.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import crowdfund.DAO.FundraisingDao;
import crowdfund.bean.CampaignBean;


@WebServlet("/CrowdFundGetAllCams")
public class CrowdFundGetAllCams extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CrowdFundGetAllCams() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FundraisingDao dao = new FundraisingDao();
		List<CampaignBean> cams = dao.getAllCams();
		request.setAttribute("cams", cams);
		
		request.getRequestDispatcher("/jsp/crowdfund/GetAllCams.jsp").forward(request, response);
			
			
		
		
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
