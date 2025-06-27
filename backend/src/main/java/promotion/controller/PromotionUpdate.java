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
@WebServlet("/PromotionUpdate")
public class PromotionUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/EE0206/jsp/InsertPromotion.jsp").forward(request, response);	
//		doPost(request, response);
		String promotionId = request.getParameter("promotionId");
		promotionDao dao = new promotionDao();
		promotionBean prom = dao.getpromById(promotionId);
		
		if (prom != null) {
			request.setAttribute("prom", prom);
			request.getRequestDispatcher("jsp/promotion/PromotionUpdate.jsp").forward(request, response);
		} else {
			response.sendRedirect("PromotionAll"); 
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		promotionBean prom = new promotionBean();		
		promotionDao dao = new promotionDao();
		
		
		prom.setPromotionId(request.getParameter("promotionId"));
		prom.setTitle(request.getParameter("title"));
		prom.setDescription(request.getParameter("description"));
		prom.setType(request.getParameter("type"));
		
		String validityType = request.getParameter("validityType");
		prom.setValidityType(validityType);
		if("RELATIVE_DAYS".equals(validityType)) {
			prom.setValidityDays(request.getParameter("validityDays"));
			prom.setValidityFrom(null);
			prom.setValidityTo(null);
		} else if("FIXED_DATE".equals(validityType)) {
			prom.setValidityFrom(request.getParameter("validityFrom"));
			prom.setValidityTo(request.getParameter("validityTo"));
			prom.setValidityDays(null);
		} 
		
		prom.setActive(request.getParameter("active"));
		prom.setNote(request.getParameter("note"));
		
		promotionBean oldProm = dao.getpromById(prom.getPromotionId());
		String oldImage = (oldProm !=null) ? oldProm.getImage() : null;
		
		Part imagePart = request.getPart("image");
		if (imagePart != null && imagePart.getSize() >0 ) {
			String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
			
			String savePath = getServletContext().getRealPath("jsp/promotion/image");
			File uploadDir = new File(savePath);
			if (!uploadDir.exists()) uploadDir.mkdirs();
			
			imagePart.write(savePath + File.separator + fileName);
			prom.setImage(fileName);
		}else {
			prom.setImage(oldImage);
		}
		
		boolean success = dao.UpdatepromById(prom);
		if (success) {
			response.sendRedirect(request.getContextPath() + "/PromotionAll");
			
		}else {
			request.setAttribute("error", "新增失敗 請確認資料格式");
			request.getRequestDispatcher("jsp/promotion/PromotionUpdate.jsp").forward(request, response);	

}
	}

}

