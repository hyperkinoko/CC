package cc_employee_manager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc_employee_manager.beans.Employee;
import cc_employee_manager.dao.EmployeeDAO;
import data_input_common.Validator;

/**
 * Servlet implementation class SerchEmployee
 */
@WebServlet("/SearchEmployee")
public class SearchEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployee() {
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
			String searchEmpId = request.getParameter("empId");
			String searchname = request.getParameter("name");
			String searchpostId = request.getParameter("postId");

			ArrayList<Employee> employees = new EmployeeDAO().searchEmployees(searchEmpId, searchname, searchpostId);
			System.out.println(employees.size());
			request.setAttribute("searched_employees", employees);
			request.getRequestDispatcher("/EmployeeList.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/EmployeeSearch.jsp").forward(request, response);
		}
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

		return success;
	}

	private String validateEmpId(String input) {
		ArrayList<String> msg = new ArrayList<>();
		if(!Validator.validateRegexAllowsBlank(input, "emp[0-9]{3}")) {
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
		if(!Validator.validateStringSize(input, Employee.MAX_NAME_LENGTH)) {
			msg.add("名前は"+ Employee.MAX_NAME_LENGTH +"バイト以内で入力してください");
		}
		if(msg.isEmpty()) {
			return null;
		} else {
			return msg.stream().map(Object::toString).collect(Collectors.joining("<br />"));
		}
	}
}
