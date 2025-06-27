package promotion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import promotion.bean.promotionBean;
import promotion.dao.promotionDao;

@MultipartConfig
@WebServlet("/PromotionInsert")
public class PromotionInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PromotionInsert() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("jsp/promotion/PromotionInsert.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		promotionBean prom = new promotionBean();
		prom.setTitle(request.getParameter("title"));
		prom.setDescription(request.getParameter("description"));
		prom.setType(request.getParameter("type"));
		
		String validityType = request.getParameter("validityType");
		prom.setValidityType(validityType);
		if("RELATIVE_DAYS".equals(validityType)) {
			prom.setValidityDays(request.getParameter("validityDays"));
			prom.setValidityFrom("");
			prom.setValidityTo("");
		} else if("FIXED_DATE".equals(validityType)) {
			prom.setValidityFrom(request.getParameter("validityFrom"));
			prom.setValidityTo(request.getParameter("validityTo"));
			prom.setValidityDays("");
		} 
		
		prom.setActive(request.getParameter("active"));
		prom.setNote(request.getParameter("note"));
		
		Part imagePart = request.getPart("image");
		if (imagePart != null && imagePart.getSize() >0 ) {
			String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
			
			String savePath = getServletContext().getRealPath("jsp/promotion/image");
			File uploadDir = new File(savePath);
			if (!uploadDir.exists()) uploadDir.mkdirs();
			
			imagePart.write(savePath + File.separator + fileName);
			prom.setImage(fileName);
		}else {
			prom.setImage(null);
		}
		
		
		promotionDao dao = new promotionDao();
		promotionBean insertprom = dao.insertPromotion(prom);
		
		if (insertprom != null) {
			response.sendRedirect(request.getContextPath() + "/PromotionAll");
		}else {
			request.setAttribute("error", "新增失敗 請確認資料格式");
			request.getRequestDispatcher("jsp/promotion/PromotionInsert.jsp").forward(request, response);	
			
		}
	}

}

