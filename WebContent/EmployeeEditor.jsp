<%@page import="java.util.ArrayList"%>
<%@page import="cc_employee_manager.beans.Post"%>
<%@page import="cc_employee_manager.dao.PostDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cc_employee_manager.beans.Employee" %>
<%@ page import="cc_employee_manager.dao.EmployeeDAO"%>
<%@ page import="data_input_common.Prefecture"%>

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
</style>
</head>
<body>
<%
	int employeeId = Integer.parseInt(request.getParameter("id"));
	Employee employee;
	if(employeeId != 0) {
		employee = new EmployeeDAO().selectEmployee(employeeId);
	} else {
		employee = new Employee();
	}
%>
    <h1>社員データ<%= (employeeId == 0) ? "新規作成" : "編集" %></h1>
    <form method="post" action="CommitEmployee" enctype="multipart/form-data">
        <p>
            <label for="empId">社員ID:</label>
            <input type="text" maxlength="10" name="empId" id="empId" value="<%= employee.getEmpId() %>">
<%          if(request.getAttribute("error_empid") != null) { %>
				<div class="error"><%= request.getAttribute("error_empid") %></div>
<%			} %>
        </p>
        <p>
            <label for="name">名前:</label>
            <input type="text" maxlength="40" name="name" id="name" value="<%= employee.getName() %>">
<%          if(request.getAttribute("error_name") != null) { %>
				<div class="error"><%= request.getAttribute("error_name") %></div>
<%			} %>
        </p>
        <p>
            <label for="age">年齢:</label>
            <input type="text" name="age" id="age" value="<%= (employee.getAge() == 0) ? "" : employee.getAge() %>">
<%          if(request.getAttribute("error_age") != null) { %>
				<div class="error"><%= request.getAttribute("error_age") %></div>
<%			} %>
        </p>
        <p>
            性別:
            <label><input type="radio" name="gender" value="male" <%= (employee.getGender().equals("male")) ? "Checked" : "" %>>男性</label>
            <label><input type="radio" name="gender" value="female" <%= (employee.getGender().equals("female")) ? "Checked" : "" %>>女性</label>
            <label><input type="radio" name="gender" value="other" <%= (employee.getGender().equals("other")) ? "Checked" : "" %>>その他</label>
        </p>
        <p>
            写真:
            <%
                if (employee.getPhotoId() != 0) {
            %>
                    <div><img src=<%= "CommitPhoto?id=" + employee.getPhotoId() %>></div>
            <%
                }
            %>
<%          if(request.getAttribute("error_photo") != null) { %>
				<div class="error"><%= request.getAttribute("error_photo") %></div>
<%			} %>
             <label><input type="file" name="photo" size="50" value="ファイルを選択してください"></label>
             <label><input type="hidden" name="photoId" value=<%= employee.getPhotoId() %>></label>
        </p>
        <p>
            <label for="zip">郵便番号:</label>
            <input type="text" maxlength="100" name="zip" id="zip" value="<%= (employee.getZip() == null) ? "" : employee.getZip() %>">
<%          if(request.getAttribute("error_zip") != null) { %>
				<div class="error"><%= request.getAttribute("error_zip") %></div>
<%			} %>
        </p>
        <p>
            <label for="pref">都道府県:</label>
            <select name="pref" size="1">
<%			Prefecture[] prefectures = Prefecture.values();
            for(Prefecture pref : prefectures) { %>
            		<option value="<%= pref.getCode() %>" label="<%= pref.getFullText() %>" <%= (pref == employee.getPref()) ? "selected" : "" %>/>
<%			} %>
            </select>
        </p>
        <p>
            <label for="address">市町村以下の住所:</label>
            <input type="text" maxlength="100" name="address" id="address" value="<%= (employee.getAddress() == null) ? "" : employee.getAddress() %>">
<%          if(request.getAttribute("error_address") != null) { %>
				<div class="error"><%= request.getAttribute("error_address") %></div>
<%			} %>
        </p>
        <p>
            <label for="postId">所属:</label>
            <select name="postId" size="1">
<%			ArrayList<Post> posts = new PostDAO().selectAllPosts(); %>
			<option value="0" label="選択してください" <%= (employee.getPostId() == 0) ? "selected" : "" %>/>
<%          for(Post post : posts) { %>
            		<option value="<%= post.getId() %>" label="<%= post.getName() %>" <%= (post.getId() == employee.getPostId()) ? "selected" : "" %>/>
<%			} %>
            </select>
       </p>
        <p>
            <label for="enterDate">入社日:</label>
            <input type="text" maxlength="100" name="enterDate" id="enterDate" value="<%= (employee.getEnterDate() == null) ? "" : employee.getEnterDate() %>">
<%          if(request.getAttribute("error_entdate") != null) { %>
				<div class="error"><%= request.getAttribute("error_entdate") %></div>
<%			} %>
        </p>
        <p>
            <label for="retireDate">退社日:</label>
            <input type="text" maxlength="100" name="retireDate" id="retireDate" value="<%= (employee.getRetireDate() == null) ? "" : employee.getRetireDate() %>">
<%          if(request.getAttribute("error_retdate") != null) { %>
				<div class="error"><%= request.getAttribute("error_retdate") %></div>
<%			} %>
        </p>
        <input type="hidden" name="id" value="<%= employee.getId() %>">
        <input type="submit" value="設定">
    </form>
    <form method="post" action="EmployeeList.jsp">
        <input type="submit" value="キャンセル">
    </form>
</body>
</html>
