package cc_employee_manager.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cc_employee_manager.beans.Photo;

public class PhotoDAO {
	private final String dbUrl = "jdbc:h2:~/CodeCamp";
	private final String dbUserId = "sa";
	private final String dbPassword = "";

    public Photo selectPhoto(int id) {
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

            String sql = "SELECT * FROM PHOTO WHERE ID=" + id + ";";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Photo photo = new Photo();
				photo.setId(rs.getInt("ID"));
				photo.setContentType(rs.getString("CONTENTTYPE"));
				photo.setPhoto(rs.getBlob("PHOTO").getBinaryStream());// これを1行でするためにsetPhoto(InputStream)を書いた
				return photo;
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

	public int deletePhoto(int id) {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			String sql = "DELETE FROM PHOTO WHERE ID=" + id + ";";

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

    public int insertPhoto(Photo photo) {
		Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

            // 読み出し用SQLコマンドを準備します。
            String sql = null;
            int id = photo.getId();
            if (id == 0) {
                sql = "INSERT INTO PHOTO (CONTENTTYPE, PHOTO) VALUES(?, ?);";
            } else {
                sql = "UPDATE PHOTO SET CONTENTTYPE=?, PHOTO=? WHERE ID=" + id + ";";
            }

            System.out.println(sql);

            // ステートメントを設定します
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, photo.getContentType());
            statement.setBinaryStream(2, new ByteArrayInputStream(photo.getPhoto())); // 写真のデータをバイナリデータに変換してDBに入れる

            // データベースを更新します
            int cnt = statement.executeUpdate();
            boolean result = (cnt > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


}
