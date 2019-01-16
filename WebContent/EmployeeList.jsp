<%@page import="cc_employee_manager.dao.EmployeeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cc_employee_manager.beans.Employee"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Manager</title>
<style>
.error {
	color: red;
	font-size: smaller;
}
.success {
	color: blue;
	font-size: smaller;
}
</style>
</head>
<body>
<%
	boolean isSearchMode = false;
	ArrayList<Employee> employees = new ArrayList<>();
	if(request.getAttribute("searched_employees") == null) {
	 	employees = new EmployeeDAO().selectAllEmployees();
	} else {
		employees = (ArrayList<Employee>)request.getAttribute("searched_employees");
		isSearchMode = true;
	}
 	if(employees != null && employees.size() != 0) {
%>
		<h1>社員一覧</h1>
<% 		if(request.getAttribute("DeleteSucceed") != null) {
				if(request.getAttribute("DeleteSucceed").equals("error")) { %>
					<p class="error">削除に失敗しました</p>
<% 			} else { %>
					<p class="success">削除しました</p>
<%			}
			}
%>
		<table border="1">
			<tr>
				<th>社員ID</th>
				<th>名前</th>
				<th colspan="2">操作</th>
			</tr>
<%		for(Employee employee : employees) { %>
			<tr>
				<td><%= employee.getEmpId()%></td>
				<td><%= employee.getName()%></td>
				<td>
					<form method="post" action="EmployeeEditor.jsp">
						<input type="hidden" name="id" value="<%=employee.getId() %>">
						<input type="submit" value="編集">
					</form>
				</td>
				<td>
					<form method="post" action="DeleteEmployee">
						<input type="hidden" name="id" value="<%=employee.getId() %>">
						<input type="submit" value="削除">
					</form>
				</td>
			</tr>
<%		} %>
		</table>
<% 	} else { %>
	<p>登録されている社員はいません</p>
<%	} %>
	<form method="post" action="EmployeeEditor.jsp">
		<input type="hidden" name="id" value="0">
		<input type="submit" value="新規追加">
	</form>
	<form method="post" action="EmployeeSearch.jsp">
		<input type="submit" value="検索...">
	</form>
<% 	if(employees != null && employees.size() != 0 && !isSearchMode ) { %>
		<form method="post" action="ExportEmployee">
			<input type="submit" value="CSVファイルに出力">
		</form>
<%	} %>
</body>
</html>