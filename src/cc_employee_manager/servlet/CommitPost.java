package cc_employee_manager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc_employee_manager.beans.Post;
import cc_employee_manager.dao.PostDAO;
import data_input_common.Validator;

/**
 * Servlet implementation class CommitPost
 */
@WebServlet("/CommitPost")
public class CommitPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommitPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if (inputIsValidated(request, response)) {
			Post post = new Post();
			post.setId(Integer.parseInt(request.getParameter("id")));
			post.setName(request.getParameter("name"));

			int result;
			if (post.getId() == 0) {
				result = new PostDAO().insertPost(post);
			} else {
				result = new PostDAO().updatePost(post);
			}

			response.setContentType("text/html; charset=UTF-8");
			if (result == 1) {
				response.getWriter().println(createSuccessHTML());
			} else {
				response.getWriter().println(createErrorHTML());
			}
			response.getWriter().flush();

		} else {
			request.getRequestDispatcher("/PostEditor.jsp").forward(request, response);
		}
	}

	private String createSuccessHTML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append("<title>Employee Manager</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h1>データベース更新に成功しました</h1>");
		sb.append("<p>データが正しく更新されました</p>");
		sb.append("<p><a href=\"PostList.jsp\">戻る</a></p>");
		sb.append("</body>");
		sb.append("</html>");

		return (new String(sb));
	}

	private String createErrorHTML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append("<title>Employee Manager</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h1>データベース更新に失敗しました</h1>");
		sb.append("<p><a href=\"PostList.jsp\">戻る</a></p>");
		sb.append("</body>");
		sb.append("</html>");

		return (new String(sb));
	}

	private boolean inputIsValidated(HttpServletRequest request, HttpServletResponse response) {
		boolean success = true;

		String error_name = validateName(request.getParameter("name"));
		if(error_name != null) {
			request.setAttribute("error_name", error_name);
			success = false;
		}
		return success;
	}

	private String validateName(String input) {
		ArrayList<String> msg = new ArrayList<>();
		if(!Validator.validateRequired(input)) {
			msg.add("部署名は入力必須事項です");
		}
		if(!Validator.validateStringSize(input, Post.MAX_NAME_LENGTH)) {
			msg.add("部署名は" + Post.MAX_NAME_LENGTH + "バイト以内で入力してください");
		}
		if(msg.isEmpty()) {
			return null;
		} else {
			return msg.stream().map(Object::toString).collect(Collectors.joining("<br />"));
		}
	}
}
