package crowdfund.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import crowdfund.DAO.FundraisingDao;
import crowdfund.bean.CampaignBean;

@WebServlet("/CrowdFundSearchByCamStatus")
public class CrowdFundSearchByCamStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrowdFundSearchByCamStatus() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		FundraisingDao dao = new FundraisingDao();
		List<CampaignBean> cams = dao.SearchByStatus(status);
		request.setAttribute("cams",cams);
		request.getRequestDispatcher("/jsp/crowdfund/GetAllCams.jsp").forward(request, response);
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
