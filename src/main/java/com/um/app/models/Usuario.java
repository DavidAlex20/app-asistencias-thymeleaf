package com.um.app.models;

import org.springframework.data.annotation.Id;

public class Usuario {
	@Id
	private int id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private boolean deleted;

	private String role;

	public Usuario(String username, String email, String password, boolean deleted, String role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.deleted = deleted;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
