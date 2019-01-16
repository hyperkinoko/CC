package cc_employee_manager.beans;

import java.io.Serializable;

public class Post implements Serializable {
	private static final long serialVersionUID = 7375411078831099831L;

	private int id = 0;
	private String name = "";

    public static final int MAX_NAME_LENGTH = 40;


	public Post() {}

	public Post(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
