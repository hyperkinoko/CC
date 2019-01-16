<%@page import="cc_employee_manager.dao.PostDAO"%>
<%@page import="cc_employee_manager.beans.Post"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>社員検索</h1>
    <form method="post" action="SearchEmployee">
        <p>
            <label for="empId">社員ID:</label>
            <input type="text" maxlength="10" name="empId" id="empId">
<%          if(request.getAttribute("error_empid") != null) { %>
				<div class="error"><%= request.getAttribute("error_empid") %></div>
<%			} %>
        </p>
        <p>
            <label for="name">名前:</label>
            <input type="text" maxlength="40" name="name" id="name">
<%          if(request.getAttribute("error_name") != null) { %>
				<div class="error"><%= request.getAttribute("error_name") %></div>
<%			} %>
        </p>
        <p>
            <label for="postId">所属:</label>
            <select name="postId" size="1">
<%			ArrayList<Post> posts = new PostDAO().selectAllPosts(); %>
			<option value="0" label="選択してください" selected />
<%          for(Post post : posts) { %>
            		<option value="<%= post.getId() %>" label="<%= post.getName() %>" />
<%			} %>
            </select>
		</p>
        <input type="submit" value="検索">
    </form>
</body>
</html>