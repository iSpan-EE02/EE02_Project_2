<%@ page import="java.util.List"%>
<%@ page import="account.bean.Employee"%>
<%@ page import="account.bean.Department"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Employee loginEmp = (Employee) session.getAttribute("emp");
List<Employee> list = (List<Employee>) request.getAttribute("empList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 載入 Bootstrap 5 CSS -->

<title>員工列表</title>
</head>
<body>

<div align="center">
	<%
	if (loginEmp != null) {
	%>
	<p>
		您好，<%=loginEmp.getEmpName()%>，歡迎回來
	</p>
	<a href="<%=request.getContextPath()%>/EmployeeServlet?action=logout">登出</a>
	<%
	} else {
	%>
	<p>尚未登入</p>
	<a href="<%=request.getContextPath()%>/jsp/employee/login.jsp">登入</a>
	<%
	return;
	}
	%>
	<a href="<%=request.getContextPath()%>/EmployeeServlet?action=toAdd">新增員工</a>
	<br>
	<br>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>姓名</th>
			<th>信箱</th>
			<th>帳號/權限</th>
			<th>電話</th>
			<th>部門</th>
			<th>更新者ID</th>
			<th>更新時間</th>
			<th>操作</th>
		</tr>
		<%
		if (list != null && !list.isEmpty()) {
			for (Employee emp : list) {
				Department dep = emp.getDepartment();
		%>
		<tr>
			<td><%=emp.getEmpId()%></td>
			<td><%=emp.getEmpName()%></td>
			<td><%=emp.getEmpMail()%></td>
			<td><%=emp.getEmpACC()%></td>
			<td><%=emp.getEmpPhoneNo()%></td>
			<td><%=(dep != null) ? dep.getDepName() : "無部門"%></td>
			<td><%=emp.getEmpUpdateBy()%></td>
			<td><%=emp.getEmpUpdateAt() != null ? emp.getEmpUpdateAt() : ""%></td>
			<td>
			<a href="<%=request.getContextPath()%>/EmployeeServlet?action=edit&id=<%=emp.getEmpId()%>">編輯</a> | 
			<a href="<%=request.getContextPath()%>/EmployeeServlet?action=delete&id=<%=emp.getEmpId()%>"
				onclick="return confirm('確定刪除此員工?');">刪除</a></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="9">沒有員工資料</td>
		</tr>
		<%
		}
		%>
	</table>
	</div>
</body>
</html>