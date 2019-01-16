package cc_employee_manager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cc_employee_manager.beans.Post;

public class PostDAO {
	private final String dbUrl = "jdbc:h2:~/CodeCamp";
	private final String dbUserId = "sa";
	private final String dbPassword = "";

	public Post selectPost(int id) {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "SELECT * FROM POST WHERE ID=" + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt("ID"));
				post.setName(rs.getString("NAME"));
				return post;
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

	public int updatePost(Post post) {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "UPDATE POST SET ";
			sql += "NAME='" + post.getName() + "' ";
			sql += "WHERE ID=" + post.getId() + ";";

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

	public int insertPost(Post post) {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "INSERT INTO POST (NAME) VALUES ('" + post.getName() + "');";

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

	public ArrayList<Post> selectAllPosts() {
		Connection connection = null;
		ArrayList<Post> list = new ArrayList<>();

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "SELECT * FROM POST";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt("ID"));
				post.setName(rs.getString("NAME"));
				list.add(post);
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

	public int deletePost(int id) {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "DELETE FROM POST WHERE ID=" + id + ";";

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
}
