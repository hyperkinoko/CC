package cc_employee_manager.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class Photo implements Serializable {
 	private static final long serialVersionUID = 297350714338917508L;
 	
	/**
     * 保持データ
     */
    private int id;
    private String contentType;
    private byte[] photo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	// setPhotoのオーバーロード
    public void setPhoto(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int length;
        try {
            // 画像データのロード
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }

            // 読み込みバッファをクローズ
            inputStream.close();
            inputStream = null;

            // 書き出しバッファをクローズ
            outputStream.close();

            // バッファの取得
            this.photo = outputStream.toByteArray();

        } catch (IOException ioe) {
            this.photo = null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
