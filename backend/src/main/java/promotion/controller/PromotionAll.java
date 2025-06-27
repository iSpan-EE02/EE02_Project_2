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

@WebServlet("/PromotionAll")
public class PromotionAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    public PromotionAll() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("EE0206/jsp/InsertPromotion.jsp").forward(request, response);	
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page=1;
		int pageSize=5;
		String pageParam = request.getParameter("page");
		if (pageParam !=null) {
			page = Integer.parseInt(pageParam);
		}
		
		promotionDao dao = new promotionDao();
		List<promotionBean> proms = dao.getAllProms();
		int total = proms.size();
		
		int fromIndex = (page-1)* pageSize;
		int toIndex = Math.min(fromIndex + pageSize, total);
		
		List<promotionBean>	pageProms = proms.subList(fromIndex, toIndex);
		
		request.setAttribute("promList", pageProms);
		request.setAttribute("proms", proms);
		request.setAttribute("currentPage", page);
//		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalPages", (int)Math.ceil((double)total / pageSize));
		request.getRequestDispatcher("jsp/promotion/PromotionAll.jsp").forward(request, response);	
		}
		
	}



