package account.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

import account.bean.Employee;
import account.dao.EmployeeDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getParameter("action");

	    if ("logout".equals(action)) {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	        response.sendRedirect(request.getContextPath() + "/html/login.html");
	    } else {
	        
	        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported for this request");
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/html/login.html");
            return;
        }

		String mail = request.getParameter("empMail");
		String pw = request.getParameter("empPw");

		try {
			Employee emp = new EmployeeDAO().login(mail, pw);
			if (emp != null) {
				HttpSession session = request.getSession();
				session.setAttribute("emp", emp);
				response.sendRedirect(request.getContextPath() + "/html/index.html");
			} else {
				// 登入失敗：重導回 login.html 並附加錯誤訊息
				String error = java.net.URLEncoder.encode("帳號或密碼錯誤", "UTF-8");
				response.sendRedirect(request.getContextPath() + "/html/login.html?error=" + error);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
