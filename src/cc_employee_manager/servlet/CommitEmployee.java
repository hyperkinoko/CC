package cc_employee_manager.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cc_employee_manager.beans.Employee;
import cc_employee_manager.beans.Photo;
import cc_employee_manager.dao.EmployeeDAO;
import cc_employee_manager.dao.PhotoDAO;
import data_input_common.Prefecture;
import data_input_common.Validator;

/**
 * Servlet implementation class CommitEmployee
 */
@WebServlet("/CommitEmployee")
@MultipartConfig
public class CommitEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommitEmployee() {
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
		request.setCharacterEncoding("UTF-8");

		if (inputIsValidated(request, response)) {
			int photoId = processPhoto(request);

			Employee employee = new Employee();
			employee.setId(Integer.parseInt(request.getParameter("id")));
			employee.setEmpId(request.getParameter("empId"));
			employee.setName(request.getParameter("name"));
			if(!request.getParameter("age").equals("")) {
				employee.setAge(Integer.parseInt(request.getParameter("age")));
			}
			employee.setGender(request.getParameter("gender"));
			employee.setPhotoId(photoId);
			employee.setZip(request.getParameter("zip"));
			employee.setPref(Prefecture.getByCode(Integer.parseInt(request.getParameter("pref"))));
			employee.setAddress(request.getParameter("address"));
			employee.setPostId(Integer.parseInt(request.getParameter("postId")));
			if(!request.getParameter("enterDate").equals("")) {
				employee.setEnterDate(Date.valueOf(request.getParameter("enterDate")));
			}
			if(!request.getParameter("retireDate").equals("")) {
				employee.setRetireDate(Date.valueOf(request.getParameter("retireDate")));
			}

			int result;
			if(employee.getId() == 0) {
				result = new EmployeeDAO().insertEmployee(employee);
			} else {
				result = new EmployeeDAO().updateEmployee(employee);
			}

			response.setContentType("text/html; charset=UTF-8");
			if (result == 1) {
				response.getWriter().println(createSuccessHTML());
			} else {
				response.getWriter().println(createErrorHTML());
			}
			response.getWriter().flush();
		} else {
			request.getRequestDispatcher("/EmployeeEditor.jsp").forward(request, response);
		}
	}

	private int processPhoto(HttpServletRequest request) {
		int photoId = Integer.parseInt(request.getParameter("photoId"));
		try {
			Part part = request.getPart("photo");
			if ((part != null) && (part.getSize() > 0)) {
				// Photoデータを作成します
				Photo photo = new Photo();
				photo.setId(photoId);
				photo.setContentType(part.getContentType());
				photo.setPhoto(part.getInputStream());

				return new PhotoDAO().insertPhoto(photo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return photoId;
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
		sb.append("<h1>データベース更新に失敗しました</h1>");
		sb.append("<p><a href=\"EmployeeList.jsp\">戻る</a></p>");
		sb.append("</body>");
		sb.append("</html>");

		return (new String(sb));
	}

	private boolean inputIsValidated(HttpServletRequest request, HttpServletResponse response) {
		boolean success = true;

		String errorEmpid = validateEmpId(request.getParameter("empId"));
		if(errorEmpid != null) {
			request.setAttribute("error_empid", errorEmpid);
			success = false;
		}

		String errorName = validateName(request.getParameter("name"));
		if(errorName != null) {
			request.setAttribute("error_name", errorName);
			success = false;
		}

		String errorAge = validateAge(request.getParameter("age"));
		if(errorAge != null) {
			request.setAttribute("error_age", errorAge);
			success = false;
		}

		try {
			String errorPhoto = validatePhoto(request.getPart("photo"));
			if(errorPhoto != null) {
				request.setAttribute("error_photo", errorPhoto);
				success = false;
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		String errorZip = validateZip(request.getParameter("zip"));
		if(errorZip != null) {
			request.setAttribute("error_zip", errorZip);
			success = false;
		}

		String errorAddress = validateAddress(request.getParameter("address"));
		if(errorAddress != null) {
			request.setAttribute("error_address", errorAddress);
			success = false;
		}

		String errorEnterDate = validateDate(request.getParameter("enterDate"));
		if(errorEnterDate != null) {
			request.setAttribute("error_entdate", errorEnterDate);
			success = false;
		}

		String errorRetireDate = validateDate(request.getParameter("retireDate"));
		if(errorRetireDate != null) {
			request.setAttribute("error_retdate", errorRetireDate);
			success = false;
		}

		return success;
	}

	private String validateEmpId(String input) {
		ArrayList<String> msg = new ArrayList<>();
		if(!Validator.validateRequired(input)) {
			msg.add("社員IDは入力必須事項です");
		}
		if(!Validator.validateRegex(input, "emp[0-9]{3}")) {
			msg.add("社員IDは「emp003」のように入力してください");
		}
		if(msg.isEmpty()) {
			return null;
		} else {
			return msg.stream().map(Object::toString).collect(Collectors.joining("<br />"));
		}
	}

	private String validateName(String input) {
		ArrayList<String> msg = new ArrayList<>();
		if(!Validator.validateRequired(input)) {
			msg.add("名前は入力必須事項です");
		}
		if(!Validator.validateStringSize(input, Employee.MAX_NAME_LENGTH)) {
			msg.add("名前は"+ Employee.MAX_NAME_LENGTH +"バイト以内で入力してください");
		}
		if(msg.isEmpty()) {
			return null;
		} else {
			return msg.stream().map(Object::toString).collect(Collectors.joining("<br />"));
		}
	}

	private String validateAge(String input) {
		ArrayList<String> msg = new ArrayList<>();
		if(!Validator.validateNumberAllowsBlank(input)) {
			msg.add("半角数字で入力してください");
		}
		if(msg.isEmpty()) {
			return null;
		} else {
			return msg.stream().map(Object::toString).collect(Collectors.joining("<br />"));
		}
	}

	private String validateZip(String input) {
		ArrayList<String> msg = new ArrayList<>();
		if(!Validator.validateZipAllowsBlank(input)) {
			msg.add("7桁の半角数字で入力してください");
		}
		if(msg.isEmpty()) {
			return null;
		} else {
			return msg.stream().map(Object::toString).collect(Collectors.joining("<br />"));
		}
	}

	private String validateAddress(String input) {
		ArrayList<String> msg = new ArrayList<>();
		if(!Validator.validateStringSize(input, Employee.MAX_ADDRESS_LENGTH)) {
			msg.add("住所は"+ Employee.MAX_ADDRESS_LENGTH +"バイト以内で入力してください");
		}
		if(msg.isEmpty()) {
			return null;
		} else {
			return msg.stream().map(Object::toString).collect(Collectors.joining("<br />"));
		}
	}

	private String validateDate(String input) {
		ArrayList<String> msg = new ArrayList<>();
		if(!Validator.validateDateAllowsBlank(input)) {
			msg.add("日付はYYYY-MM-DDの形式で入力してください");
		}
		if(msg.isEmpty()) {
			return null;
		} else {
			return msg.stream().map(Object::toString).collect(Collectors.joining("<br />"));
		}
	}

	private String validatePhoto(Part part) {
		ArrayList<String> msg = new ArrayList<>();
		if(!Validator.validateFileSize(part.getSize(), 2000000)) {
			msg.add("ファイルが大きすぎます（2Mまで）");
		}
		if(msg.isEmpty()) {
			return null;
		} else {
			return msg.stream().map(Object::toString).collect(Collectors.joining("<br />"));
		}
	}


}
