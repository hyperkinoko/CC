<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cc_employee_manager.beans.Post" %>
<%@ page import="cc_employee_manager.dao.PostDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	int id = Integer.parseInt(request.getParameter("id"));
	Post post;
	if(id != 0) {
		post = new PostDAO().selectPost(id);
	} else {
		post = new Post();
	}
%>
<title>Employee Manager</title>
<style>
.error {
	color: red;
	font-size: smaller;
}
</style>
</head>
<body>
    <h1>部署データ<%= (id == 0) ? "新規作成" : "編集" %></h1>
    <form method="post" action="CommitPost">
<%		if(id != 0) {%>
        <p>部署ID: <%= post.getId() %></p>
<%		} %>
        <p>
            <label for="name">部署名:</label>
            <input type="text" maxlength="40" name="name" id="name" value="<%= post.getName() %>">
<%          if(request.getAttribute("error_name") != null) { %>
				<div class="error"><%= request.getAttribute("error_name") %></div>
<%			} %>
        </p>
        <input type="hidden" name="id" value="<%= post.getId() %>">
        <input type="submit" value="設定">
    </form>
    <form method="post" action="PostList.jsp">
        <input type="submit" value="キャンセル">
    </form>
</body>
</html>
