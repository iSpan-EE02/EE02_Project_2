<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="account.bean.Department"%>
<%
List<Department> depList = (List<Department>) request.getAttribute("depList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增員工</title>
</head>
<body>
<h2>新增員工</h2>
	<form method="post" action="<%=request.getContextPath()%>/EmployeeServlet">
		<input type="hidden" name="action" value="add" />
		 姓名：<input type="text" name="name" required><br>
		 信箱：<input	type="email" name="email" required><br>
		 密碼：<input	type="password" name="password" required><br>
		 部門： <select name="depId" required>
			<option value="">-- 請選擇部門 --</option>
			<%if (depList != null) {
				for (Department dep : depList) {%>
			<option value="<%=dep.getDepId()%>"><%=dep.getDepName()%></option>
			<%	}
			}	%>
			
		</select><br>
		
    		權限：
        		<select name="empACC" required>
            		<option value="admin">管理員</option>
            		<option value="staff">一般員工</option>
        		</select><br>
 			電話：<input type="text" name="phoneNo" required><br>   		
		
 <input type="submit" value="新增">
	</form>
</body>
</html>