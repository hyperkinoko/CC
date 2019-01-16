<%@page import="cc_employee_manager.dao.PostDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cc_employee_manager.beans.Post"%>
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
	PostDAO dao = new PostDAO();
 	ArrayList<Post> posts = dao.selectAllPosts();
 	if(posts != null && posts.size() != 0) {
%>
	<h1>部署一覧</h1>
<% 	if(request.getAttribute("DeleteSucceed") != null) {
		if(request.getAttribute("DeleteSucceed").equals("error")) { %>
			<p class="error">削除に失敗しました</p>
<% 		}
		if(request.getAttribute("DeleteSucceed").equals("success")) { %>
			<p class="success">削除しました</p>
<%		}
	}
%>
	<table border="1">
		<tr>
			<th>部署ID</th>
			<th>部署名</th>
			<th colspan="2">操作</th>
		</tr>
<%	for(Post post : posts) { %>
		<tr>
			<td><%= post.getId()%></td>
			<td><%= post.getName()%></td>
			<td>
				<form method="post" action="PostEditor.jsp">
					<input type="hidden" name="id" value="<%=post.getId() %>">
					<input type="submit" value="編集">
				</form>
			</td>
			<td>
				<form method="post" action="DeletePost">
					<input type="hidden" name="id" value="<%=post.getId() %>">
					<input type="submit" value="削除">
				</form>
			</td>
		</tr>
<% } %>
	</table>
<% } else { %>
	<p>登録されている部署はありません</p>
<% } %>
	<form method="post" action="PostEditor.jsp">
		<input type="hidden" name="id" value="0">
		<input type="submit" value="新規追加">
	</form>
</body>
</html>