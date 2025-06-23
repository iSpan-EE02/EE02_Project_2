<%@ page import="java.util.List" %>
<%@ page import="account.bean.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Employee> list = (List<Employee>) request.getAttribute("empList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工列表</title>
</head>
<body>
	<a href="add.jsp">新增員工</a>
	<br>
	<br>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>姓名</th>
			<th>信箱</th>
			<th>操作</th>
		</tr>
		<%
		for (Employee emp : list) {
		%>
		<tr>
			<td><%=emp.getEmpId()%></td>
			<td><%=emp.getEmpName()%></td>
			<td><%=emp.getEmpMail()%></td>
			<td><a href="edit.jsp?id=<%=emp.getEmpId()%>">編輯</a> | <a
				href="<%=request.getContextPath()%>/employee?action=delete&id=<%=emp.getEmpId()%>">刪除</a>
			</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>