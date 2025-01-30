package com.um.app.models.database;

import org.springframework.data.annotation.Id;


public class Users {
	@Id
	private int id;
	
	private String username;
	
	private String password;

	private String role;

	private boolean activo;

	public Users(){}
	public Users(String username, String password, String role, boolean activo) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.activo = activo;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
