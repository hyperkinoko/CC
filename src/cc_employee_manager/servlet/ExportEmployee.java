package cc_employee_manager.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc_employee_manager.beans.Employee;
import cc_employee_manager.dao.EmployeeDAO;

/**
 * Servlet implementation class ExportEmployee
 */
@WebServlet("/ExportEmployee")
public class ExportEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 書き出し先のファイル名
		final String filename = "EmployeeData.csv";
		ServletContext servletContext = this.getServletConfig().getServletContext();
		final File file = new File(servletContext.getRealPath("/") + filename);
		System.out.println(servletContext.getRealPath("/"));

		// 全従業員データを取得する
		ArrayList<Employee> employees = new EmployeeDAO().selectAllEmployees();
		if(employees == null || employees.size() == 0) {
			// エラーページを表示
	        response.setContentType("text/html; charset=UTF-8");
	        response.getWriter().println(createErrorHTML());
	        response.getWriter().flush();
	        return;
		}

		// 全従業員データを書き出したファイルを作成する
		try
		(
			BufferedWriter writer = Files.newBufferedWriter(file.toPath(), Charset.defaultCharset());
		) {
			// ヘッダーを書き込む
			writer.write("ID, EMPID, NAME, AGE, GENDER, PHOTOID, ZIP, PREF, ADRESS, POSTID, ENTERDATE, RETIREDATE");
			writer.newLine();

			for(Employee employee : employees) {
				// 一人ぶんのデータを読み込んで、配列に → joinでカンマ区切りの1行データに
				String line = String.join(", ", employee.fieldToArray());
				writer.write(line);
				writer.newLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
			// エラーページを表示
	        response.setContentType("text/html; charset=UTF-8");
	        response.getWriter().println(createErrorHTML());
	        response.getWriter().flush();
	        return;
		}

		// ダウンロードリンクを表示
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println(createDownloadHTML(filename));
        response.getWriter().flush();
	}

    private String createDownloadHTML(String filename) {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        sb.append("<title>Employee Manager</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>ファイル生成に成功しました</h1>");
        sb.append("<p>データベースから正しくファイルが生成されました</p>");
        sb.append("<p><a href=\"").append(filename).append("\" download=\"").append(filename).append("\">こちらからダウンロード</a></p>");
        sb.append("<p><a href=\"EmployeeList.jsp\">戻る</a></p>");
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
        sb.append("<h1>ファイル生成に失敗しました</h1>");
        sb.append("<p><a href=\"EmployeeList.jsp\">戻る</a></p>");
        sb.append("</body>");
        sb.append("</html>");

        return (new String(sb));
    }

}
