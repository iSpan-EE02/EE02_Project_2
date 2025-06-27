package account.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import account.bean.*;
import account.dao.*;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO dao = new MemberDAO();
	private MemberRankDAO rankDao = new MemberRankDAO();

	public MemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "list";

		switch (action) {
		case "delete":
			delete(request, response);
			break;
		case "edit":
			edit(request, response);
			break;
		case "toAdd":
			toAdd(request, response);
			break;
		default:
			list(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("login".equals(action)) {
	        login(request, response);
	        return;
	    }

	  
		// 權限驗證
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("emp") == null) {
			response.sendRedirect(request.getContextPath() + "/jsp/employee/login.jsp");
			return;
		}

		Employee updater = (Employee) session.getAttribute("emp");
		

		
	    switch (action) {
	        case "add":
	            insert(request, response, updater);
	            break;
	        case "update":
	            update(request, response, updater);
	            break;
	        default:
	            response.sendRedirect(request.getContextPath() + "/MemberServlet?action=list");
	            break;
	    }
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    MemberDAO dao = new MemberDAO();
	    Member member = dao.getByEmail(email); 

	    if (member != null && member.getMemPw() != null && member.getMemPw().equals(password)) {
	        // 登入成功
	        request.getSession().setAttribute("member", member);
	        response.sendRedirect(request.getContextPath() + "/MemberServlet?action=list");
	    } else {
	        // 登入失敗
	        request.setAttribute("error", "帳號或密碼錯誤");
	        request.getRequestDispatcher("/jsp/member/login.jsp").forward(request, response);
	    }
	}

	// --- CRUD 方法 ---

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> memList = dao.getAll();
		request.setAttribute("memList", memList);
		request.getRequestDispatcher("/jsp/member/list.jsp").forward(request, response);
	}

	private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MemberRank> rankList = rankDao.getAll();
		request.setAttribute("rankList", rankList);
		request.getRequestDispatcher("/jsp/member/add.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Member member = dao.getById(id);
		List<MemberRank> rankList = rankDao.getAll();
		request.setAttribute("member", member);
		request.setAttribute("rankList", rankList);
		request.getRequestDispatcher("/jsp/member/edit.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		response.sendRedirect(request.getContextPath() + "/MemberServlet?action=list");
	}

	private void insert(HttpServletRequest request, HttpServletResponse response, Employee updater)
			throws ServletException, IOException {
		Member member = parseMember(request);
		member.setMemDct(new Timestamp(System.currentTimeMillis()));
		member.setMemUpdateBy(updater);
		member.setMemUpdateAt(new Timestamp(System.currentTimeMillis()));

		dao.insert(member);
		response.sendRedirect(request.getContextPath() + "/MemberServlet?action=list");
	}

	private void update(HttpServletRequest request, HttpServletResponse response, Employee updater)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("memId"));
		Member member = parseMember(request);
		member.setMemId(id);
		member.setMemUpdateBy(updater);
		member.setMemUpdateAt(new Timestamp(System.currentTimeMillis()));

		dao.update(member);
		response.sendRedirect(request.getContextPath() + "/MemberServlet?action=list");
	}

	// --- 表單欄位轉物件 ---
	private Member parseMember(HttpServletRequest request) {
		Member member = new Member();
		member.setMemName(request.getParameter("memName"));
		member.setMemGd(request.getParameter("memGd"));
		member.setMemPn(request.getParameter("memPn"));
		member.setMemIn(request.getParameter("memIn"));
		member.setMemAdr(request.getParameter("memAdr"));
		member.setMemMail(request.getParameter("memMail"));
		member.setMemPw(request.getParameter("memPw"));
		member.setMemBd(Date.valueOf(request.getParameter("memBd")));

		// 會員等級
		int rankId = Integer.parseInt(request.getParameter("rankId"));
		MemberRank rank = new MemberRank();
		rank.setRankId(rankId);
		member.setRank(rank);

		// 贈送對象
		String giftStr = request.getParameter("giftToUserId");
		if (giftStr != null && !giftStr.isEmpty()) {
			Member gift = new Member();
			gift.setMemId(Integer.parseInt(giftStr));
			member.setGiftToUser(gift);
		}

		return member;
	}
}
