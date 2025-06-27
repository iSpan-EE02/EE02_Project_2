package promotion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import promotion.bean.promotionBean;
import promotion.dao.promotionDao;

@WebServlet("/PromotionDelete")
public class PromotionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("EE0206/jsp/InsertPromotion.jsp").forward(request, response);	
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String prom = request.getParameter("promotionId");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		promotionDao dao = new promotionDao();
		boolean success = dao.DeletepromById(prom);
		if (success) {
			response.sendRedirect(request.getContextPath() + "/PromotionAll");
					
		}else {
			request.setAttribute("error", "新增失敗 請確認資料格式");
			request.getRequestDispatcher("jsp/promotion/PromotionDelete.jsp").forward(request, response);	
		
			}
		}
	}



