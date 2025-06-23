package account.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import account.bean.Employee;
import account.dao.EmployeeDAO;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String action = request.getParameter("action");
		
		if ("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDAO dao = new EmployeeDAO();
			dao.delete(id);
			response.sendRedirect("employee?action=list");
		} else if ("edit".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee emp = new EmployeeDAO().getById(id);
			request.setAttribute("emp", emp);
			request.getRequestDispatcher("employee/edit.jsp").forward(request, response);
		} else {
			// 預設顯示全部
			List<Employee> list = new EmployeeDAO().getAll();
			request.setAttribute("empList", list);
			request.getRequestDispatcher("employee/list.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			try {
				EmployeeDAO dao = new EmployeeDAO();
				Employee emp = dao.login(email, password);
				if (emp != null) {
					HttpSession session = request.getSession();
					session.setAttribute("emp", emp);
					response.sendRedirect("employee/list.jsp");
				} else {
					request.setAttribute("error", "登入失敗，請檢查帳號密碼");
					request.getRequestDispatcher("employee/login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		
		EmployeeDAO dao = new EmployeeDAO();

		if ("add".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pw = request.getParameter("password");
			Employee emp = new Employee();
			emp.setEmpName(name);
			emp.setEmpMail(email);
			emp.setEmpPassword(pw);
			dao.insert(emp);
			List<Employee> empList = dao.getAll();
			request.setAttribute("empList", empList);
			request.getRequestDispatcher("employee/list.jsp").forward(request, response);
		} else if ("update".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			Employee emp = new Employee();
			emp.setEmpId(id);
			emp.setEmpName(name);
			emp.setEmpMail(email);
			dao.update(emp);
			List<Employee> empList = dao.getAll();
			request.setAttribute("empList", empList);
			request.getRequestDispatcher("employee/list.jsp").forward(request, response);
		}
	}

}
