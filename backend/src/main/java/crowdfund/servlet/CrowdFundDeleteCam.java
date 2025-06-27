package crowdfund.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import crowdfund.DAO.FundraisingDao;

@WebServlet("/CrowdFundDeleteCam")
public class CrowdFundDeleteCam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrowdFundDeleteCam() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("campaign_id");
		try {
            int campaignId = Integer.parseInt(idStr);
            FundraisingDao dao = new FundraisingDao();
            dao.deleteCamById(campaignId);
            request.setAttribute("cams", dao.getAllCams());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
		request.getRequestDispatcher("/jsp/crowdfund/GetAllCams.jsp").forward(request, response);

}
}
