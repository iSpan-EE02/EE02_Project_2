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

import account.bean.Department;
import account.bean.Employee;
import account.dao.DepartmentDAO;
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
		EmployeeDAO dao = new EmployeeDAO();

//		if ("logout".equals(action)) {
//			HttpSession session = request.getSession(false);
//			if (session != null) {
//				session.invalidate();
//			}
//			request.setAttribute("message", "已成功登出");
//			response.sendRedirect(request.getContextPath() + "/jsp/employee/login.jsp");
//			return;}
		 if ("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.delete(id);
			response.sendRedirect("EmployeeServlet?action=list");
		} else if ("edit".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee emp = new EmployeeDAO().getById(id);
			List<Department> depList = new DepartmentDAO().getAll();
			request.setAttribute("emp", emp);
			request.setAttribute("depList", depList);
			request.getRequestDispatcher("/jsp/employee/edit.jsp").forward(request, response);
		} else if ("toAdd".equals(action)) {
			// 取得部門清單
			List<Department> depList = new DepartmentDAO().getAll();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher("/jsp/employee/add.jsp").forward(request, response);
			return;
		} else {
			// 預設顯示全部
			List<Employee> list = new EmployeeDAO().getAll();
			System.out.println("取得員工筆數：" + list.size());
			request.setAttribute("empList", list);
			request.getRequestDispatcher("/jsp/employee/list.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		EmployeeDAO dao = new EmployeeDAO();

		if ("login".equals(action)) {
			String mail = request.getParameter("empMail");
			String pw = request.getParameter("empPw");
			String target = request.getParameter("target");

			try {
				Employee emp = dao.login(mail, pw);
				if (emp != null) {
					HttpSession session = request.getSession();
					session.setAttribute("emp", emp);
//					response.sendRedirect(request.getContextPath() + "/html/index.html");
					if ("member".equals(target)) {
						response.sendRedirect(request.getContextPath() + "/MemberServlet?action=list");
					} else if ("employee".equals(target)) {
						response.sendRedirect(request.getContextPath() + "/EmployeeServlet?action=list");
					} else {
						response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
					}
				} else {
					request.setAttribute("error", "帳號或密碼錯誤");
					request.getRequestDispatcher("/jsp/employee/login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				throw new ServletException(e); // 或使用 e.printStackTrace() 做除錯
			}
			return;
		}
		// 權限驗證
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("emp") == null) {
			response.sendRedirect(request.getContextPath() + "/jsp/employee/login.jsp");
			return;
		}
		Employee loginEmp = (Employee) session.getAttribute("emp");
		int loginEmpId = loginEmp.getEmpId();

		if ("add".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pw = request.getParameter("password");
			String empACC = request.getParameter("empACC");
			int phoneNo = Integer.parseInt(request.getParameter("phoneNo"));
			int depId = Integer.parseInt(request.getParameter("depId"));
			Employee emp = new Employee();
			emp.setEmpName(name);
			emp.setEmpMail(email);
			emp.setEmpPassword(pw);
			emp.setEmpACC(empACC);
			emp.setEmpPhoneNo(phoneNo);
			Department dep = new Department();
			dep.setDepId(depId);
			emp.setDepartment(dep);
			emp.setEmpUpdateBy(loginEmpId);
			emp.setEmpUpdateAt(new java.sql.Timestamp(System.currentTimeMillis()));

			dao.insert(emp);
			List<Employee> empList = dao.getAll();
			request.setAttribute("empList", empList);
			request.getRequestDispatcher("/jsp/employee/list.jsp").forward(request, response);
		} else if ("update".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String empACC = request.getParameter("empACC");
			int phoneNo = Integer.parseInt(request.getParameter("phoneNo"));
			int depId = Integer.parseInt(request.getParameter("depId"));
			Employee emp = new Employee();
			emp.setEmpId(id);
			emp.setEmpName(name);
			emp.setEmpMail(email);
			emp.setEmpACC(empACC);
			emp.setEmpPhoneNo(phoneNo);
			Department dep = new Department();
			dep.setDepId(depId);
			emp.setDepartment(dep);
			emp.setEmpUpdateBy(loginEmpId);
			emp.setEmpUpdateAt(new java.sql.Timestamp(System.currentTimeMillis()));
			dao.update(emp);
			List<Employee> empList = dao.getAll();
			request.setAttribute("empList", empList);
			request.getRequestDispatcher("/jsp/employee/list.jsp").forward(request, response);
		}
	}

}
