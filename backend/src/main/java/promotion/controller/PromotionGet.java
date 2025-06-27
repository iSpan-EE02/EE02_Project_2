package promotion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import promotion.bean.promotionBean;
import promotion.dao.promotionDao;

@WebServlet("/PromotionGet")
public class PromotionGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String prom = request.getParameter("prom");
		String keyword = request.getParameter("keyword");
		String type = request.getParameter("type");
		
		if (keyword !=null)keyword = keyword.trim();
		if (type !=null)type = type.trim();
		
		//改用DAO查
		promotionDao dao = new promotionDao();
		List<promotionBean> promList;
		
		 
			if((keyword == null || keyword.isEmpty()) && (type ==null || type.isEmpty())) {
			request.getRequestDispatcher("jsp/promotion/PromotionAll.jsp").forward(request, response);	
			return;
			}
			
			if((keyword !=null && !keyword.isEmpty()) && (type !=null && !type.isEmpty())) {
			
			promList = dao.searchByTitleAndType(keyword,type);
			}else if (keyword !=null && !keyword.isEmpty()) {
				promList = dao.searchByTitle(keyword);
			} else { 
				promList= dao.searchByType(type);
			}
			request.setAttribute("promList", promList);
			request.getRequestDispatcher("jsp/promotion/PromotionAll.jsp").forward(request, response);	
	}}	
		
	
	



