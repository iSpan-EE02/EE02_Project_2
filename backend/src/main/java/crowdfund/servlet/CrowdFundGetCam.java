package crowdfund.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import crowdfund.DAO.FundraisingDao;
import crowdfund.bean.CampaignBean;
@WebServlet("/CrowdFundGetCam")
public class CrowdFundGetCam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrowdFundGetCam() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String campaignID = request.getParameter("campaign_id");
		CampaignBean cam = new CampaignBean();
		FundraisingDao dao = new FundraisingDao();
		
		cam = dao.getCamById(Integer.parseInt(campaignID));
		request.setAttribute("cam", cam);
		request.getRequestDispatcher("/jsp/crowdfund/GetCam.jsp").forward(request, response);
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
