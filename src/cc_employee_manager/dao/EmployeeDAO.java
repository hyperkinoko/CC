package cc_employee_manager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import cc_employee_manager.beans.Employee;
import data_input_common.Prefecture;

public class EmployeeDAO {
	private final String dbUrl = "jdbc:h2:~/CodeCamp";
	private final String dbUserId = "sa";
	private final String dbPassword = "";

	public Employee selectEmployee(int id) {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "SELECT * FROM EMPLOYEE WHERE ID=" + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("ID"));
				employee.setEmpId(rs.getString("EMPID"));
				employee.setName(rs.getString("NAME"));
				employee.setAge(rs.getInt("AGE"));
				employee.setGender(rs.getString("GENDER"));
				employee.setPhotoId(rs.getInt("PHOTOID"));
				employee.setZip(rs.getString("ZIP"));
				employee.setPref(Prefecture.getByCode(rs.getInt("PREF")));
				employee.setAddress(rs.getString("ADDRESS"));
				employee.setPostId(rs.getInt("POSTID"));
				employee.setEnterDate(rs.getDate("ENTDATE"));
				employee.setRetireDate(rs.getDate("RETDATE"));

				return employee;
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public int updateEmployee(Employee employee) {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "UPDATE EMPLOYEE SET ";
			sql += "EMPID='" + employee.getEmpId() + "', ";
			sql += "NAME='" + employee.getName() + "', ";
			sql += "AGE=" + employee.getAge() + ", ";
			sql += "GENDER='" + employee.getGender() + "', ";
			sql += "PHOTOID=" + ((employee.getPhotoId() == 0) ? "null" : employee.getPhotoId()) + ", ";
			sql += "ZIP='" + employee.getZip() + "', ";
			sql += "PREF=" + employee.getPref().getCode() + ",";
			sql += "ADDRESS='" + employee.getAddress() + "', ";
			sql += "POSTID=" + ((employee.getPostId() == 0) ? "null" : employee.getPostId()) + ", ";
			if(employee.getEnterDate() == null) {
				sql += "ENTDATE=null, ";
			} else {
				sql += "ENTDATE='" + employee.getEnterDate() + "', ";
			}
			if(employee.getRetireDate() == null) {
				sql +=  "RETDATE=null ";
			} else {
				sql += "RETDATE='" + employee.getRetireDate() + "' ";
			}
			sql += "WHERE ID=" + employee.getId() + ";";

			System.out.println(sql);

			PreparedStatement statement = connection.prepareStatement(sql);
			int changed = statement.executeUpdate();
			if(changed == 1) {
				return changed;
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public int insertEmployee(Employee employee) {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "INSERT INTO EMPLOYEE (EMPID, NAME, AGE, GENDER, PHOTOID, ZIP, PREF, ADDRESS, POSTID, ENTDATE, RETDATE) VALUES (";
			sql += "'" + employee.getEmpId() + "', ";
			sql += "'" + employee.getName() + "', ";
			sql += employee.getAge() + ", ";
			sql += "'" + employee.getGender() + "', ";
			sql += ((employee.getPhotoId() == 0) ? "null" : employee.getPhotoId()) + ", ";
			sql += "'" + employee.getZip() + "', ";
			sql += employee.getPref().getCode() + ",";
			sql += "'" + employee.getAddress() + "', ";
			sql += ((employee.getPostId() == 0) ? "null" : employee.getPostId()) + ", ";
			if(employee.getEnterDate() == null) {
				sql += "null, ";
			} else {
				sql += "'" + employee.getEnterDate() + "', ";
			}
			if(employee.getRetireDate() == null) {
				sql +=  "null);";
			} else {
				sql += "'" + employee.getRetireDate() + "');";
			}
			System.out.println(sql);

			PreparedStatement statement = connection.prepareStatement(sql);
			int inserted = statement.executeUpdate();
			if(inserted == 1) {
				return inserted;
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public ArrayList<Employee> selectAllEmployees() {
		Connection connection = null;
		ArrayList<Employee> list = new ArrayList<>();

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "SELECT * FROM EMPLOYEE";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("ID"));
				employee.setEmpId(rs.getString("EMPID"));
				employee.setName(rs.getString("NAME"));
				employee.setAge(rs.getInt("AGE"));
				employee.setGender(rs.getString("GENDER"));
				employee.setPhotoId(rs.getInt("PHOTOID"));
				employee.setZip(rs.getString("ZIP"));
				employee.setPref(Prefecture.getByCode(rs.getInt("PREF")));
				employee.setAddress(rs.getString("ADDRESS"));
				employee.setPostId(rs.getInt("POSTID"));
				employee.setEnterDate(rs.getDate("ENTDATE"));
				employee.setRetireDate(rs.getDate("RETDATE"));
				list.add(employee);
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public int deleteEmployee(int id) {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "DELETE FROM EMPLOYEE WHERE ID=" + id + ";";

			PreparedStatement statement = connection.prepareStatement(sql);
			int deleted = statement.executeUpdate();
			if(deleted == 1) {
				return deleted;
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public ArrayList<Employee> searchEmployees(String empId, String name, String postId) {
		Connection connection = null;
		ArrayList<Employee> list = new ArrayList<>();

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "SELECT * FROM EMPLOYEE";
			ArrayList<String> where = new ArrayList<>();
			if(empId != null && !empId.equals("")) {
				where.add("EMPID='" + empId + "'");
			}
			if(name != null && !name.equals("")) {
				where.add("NAME LIKE '%" + name + "%'");
			}
			if(postId != null && !postId.equals("") && !postId.equals("0")) {
				where.add("POSTID=" + postId);
			}
			if(!where.isEmpty()) {
				//sql += "WHERE" + String.join(" AND ", where);
				sql += " WHERE " + where.stream().map(Object::toString).collect(Collectors.joining(" AND "));
			}
			sql += ";";

			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("ID"));
				employee.setEmpId(rs.getString("EMPID"));
				employee.setName(rs.getString("NAME"));
				employee.setAge(rs.getInt("AGE"));
				employee.setGender(rs.getString("GENDER"));
				employee.setPhotoId(rs.getInt("PHOTOID"));
				employee.setZip(rs.getString("ZIP"));
				employee.setPref(Prefecture.getByCode(rs.getInt("PREF")));
				employee.setAddress(rs.getString("ADDRESS"));
				employee.setPostId(rs.getInt("POSTID"));
				employee.setEnterDate(rs.getDate("ENTDATE"));
				employee.setRetireDate(rs.getDate("RETDATE"));
				list.add(employee);
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
