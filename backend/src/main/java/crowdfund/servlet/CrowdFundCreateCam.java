package crowdfund.servlet;

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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import crowdfund.DAO.FundraisingDao;
import crowdfund.bean.CampaignBean;


@MultipartConfig
@WebServlet("/CrowdFundCreateCam")
public class CrowdFundCreateCam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrowdFundCreateCam() {
        super();
    }
    Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		String campaignName = request.getParameter("campaign_name");
		String campaignCategory = request.getParameter("campaign_category");
		
		String goalAmountStr = request.getParameter("goal_amount");

		String currentAmountStr = request.getParameter("current_amount");

		String startDateStr = request.getParameter("start_date");

		String endDateStr = request.getParameter("end_date");
		
		String coverImage = request.getParameter("cover_image");
		
		String status = request.getParameter("status");

		String creatorId = request.getParameter("creator_id");
		
		String description = request.getParameter("description");
		
		List<String> errors = new ArrayList<>();
		if(campaignName == null || campaignName.trim().isEmpty()) {
			errors.add("活動名稱不能為空");
		}
		 if (goalAmountStr == null || goalAmountStr.trim().isEmpty()) {
		        errors.add("目標金額不能為空");
		    }
	    if (creatorId == null || creatorId.trim().isEmpty()) {
		        errors.add("建立者ID不能為空");
		    }
	    
	    double goalAmount = 0;
	    double currentAmount = 0;
	    int creatorInt = 0;
	    Date startDate = null;
	    Date endDate = null;
	    
	    try {
	        goalAmount = Double.parseDouble(goalAmountStr);
	        if (goalAmount <= 0) {
	        	errors.add("目標金額必須為正數");
	        }
	    } catch (NumberFormatException e) {
	        errors.add("目標金額格式不正確");
	    }

	    try {
	        currentAmount = Double.parseDouble(currentAmountStr);
	        if (currentAmount < 0) {
	        	errors.add("目前金額不能為負");
	        }
	    } catch (NumberFormatException e) {
	        errors.add("目前金額格式不正確");
	    }

	    try {
	        startDate = Date.valueOf(startDateStr);
	    } catch (Exception e) {
	        errors.add("開始日期格式錯誤");
	    }

	    try {
	        endDate = Date.valueOf(endDateStr);
	    } catch (Exception e) {
	        errors.add("結束日期格式錯誤");
	    }

	    try {
	        creatorInt = Integer.parseInt(creatorId);
	    } catch (NumberFormatException e) {
	        errors.add("建立者ID格式錯誤");
	    }
	    
//	    上傳照片
		Part imagePart = request.getPart("cover_image");
        String imagePath = null;

        if (imagePart != null && imagePart.getSize() > 0) {
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("/jsp/crowdfund/uploads");

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String filePath = uploadPath + File.separator + fileName;
            imagePart.write(filePath);

            imagePath = "jsp/crowdfund/uploads/" + fileName; // 儲存相對路徑
        } else {
            imagePath = "uploads/default.jpg"; // 預設圖片
        }
	    
	    
	    
	    
	    
	    if (!errors.isEmpty()) {
	        // 把錯誤訊息送回前端顯示
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/jsp/crowdfund/InsertCam.jsp").forward(request, response);
	        return;
	    }
	    
	    
	    
		
		CampaignBean cam = new CampaignBean();
		cam.setCampaignName(campaignName);
		cam.setCampaignCategory(campaignCategory);
		cam.setGoalAmount(goalAmount);
		cam.setCurrentAmount(currentAmount);
		cam.setStartDate(startDate);
		cam.setEndDate(endDate);
		cam.setCoverImage(imagePath);
		cam.setStatus(status);
		cam.setCreatorID(creatorInt);
		cam.setDescription(description);
		FundraisingDao dao = new FundraisingDao();
		dao.addCam(cam);
			
//		request.setAttribute("cam", cam);
		response.sendRedirect("CrowdFundGetAllCams");
			
		
	}

}
