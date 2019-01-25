package com.clone.mvn_03.model;

import java.io.Serializable;
import java.util.List;

public class MySystem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6778160711122046875L;
	private int id;
	private String name;
	private User user;
	private List<User> users;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
