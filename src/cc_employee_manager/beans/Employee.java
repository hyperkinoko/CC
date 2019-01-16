package cc_employee_manager.beans;

import java.io.Serializable;
import java.sql.Date;

import data_input_common.Prefecture;

public class Employee implements Serializable {
	private static final long serialVersionUID = -1802021034771138293L;
	// ID
	private int id = 0;
	// 社員ID
	private String empId = "";
	// 名前
	private String name = "";
	// 年齢
	private int age = 0;
	// 性別
	private String gender = "other";
	// 写真ID
	private int photoId = 0;
	// 郵便番号
	private String zip = "";
	// 都道府県
	private Prefecture pref = Prefecture.NONE;
	// 住所
	private String address = "";
	// 部署ID
	private int postId = 0;
	// 入社日
	private Date enterDate;
	// 退社日
	private Date retireDate;


	public static final int MAX_NAME_LENGTH = 40;
	public static final int MAX_ADDRESS_LENGTH = 100;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Prefecture getPref() {
		return pref;
	}

	public void setPref(Prefecture pref) {
		this.pref = pref;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public Date getRetireDate() {
		return retireDate;
	}

	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}

	public String[] fieldToArray() {
		String[] array = new String[12];
		array[0] = String.valueOf(id);
		array[1] = empId;
		array[2] = name;
		array[3] = String.valueOf(age);
		array[4] = gender;
		array[5] = String.valueOf(photoId);
		array[6] = zip;
		array[7] = pref.getFullText();
		array[8] = address;
		array[9] = String.valueOf(postId);
		array[10] = (enterDate == null) ? "" : enterDate.toString();
		array[11] = (retireDate == null) ? "" : retireDate.toString();
		return array;
	}

}
