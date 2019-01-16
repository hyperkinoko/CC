package cc_employee_manager.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc_employee_manager.beans.Photo;
import cc_employee_manager.dao.PhotoDAO;

/**
 * Servlet implementation class PhotoImage
 */
@WebServlet("/CommitPhoto")
public class CommitPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストを解析します
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null) {
            // ID を取得します
            try {
                id = Integer.valueOf(idStr);
            } catch (NumberFormatException nfe) {
                // エラー表示
                nfe.printStackTrace();
                id = 0;
            }
        }

        // 対応する画像を読み込みます
        if (id != 0) {
            Photo photo = new PhotoDAO().selectPhoto(id);
            response.setContentType(photo.getContentType());

            ByteArrayInputStream bais = new ByteArrayInputStream(photo.getPhoto());
            ServletOutputStream sos = response.getOutputStream();
            try {
                byte[] buf = new byte[1024];
                int len;
                while ((len = bais.read(buf)) > 0) {
                    sos.write(buf, 0, len);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                bais.close();
                sos.close();
            }
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
