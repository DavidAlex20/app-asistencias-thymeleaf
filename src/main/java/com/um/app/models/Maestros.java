package com.um.app.models;

import org.springframework.data.annotation.Id;

public class Maestros {
	@Id
	private int id;
	
	private String numempleado;
	
	private String nombre;
	
	private String status;

	private String apellido;

	private int id_users;

	public Maestros(){}
	public Maestros(String numempleado, String nombre, String status, String apellido, int id_users) {
		this.numempleado = numempleado;
		this.nombre = nombre;
		this.status = status;
		this.apellido = apellido;
		this.id_users = id_users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumempleado() {
		return numempleado;
	}

	public void setNumempleado(String numempleado) {
		this.numempleado = numempleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getId_users() {
		return id_users;
	}

	public void setId_users(int id_users) {
		this.id_users = id_users;
	}
	
	@Override
	public String toString() {
		return "Maestros [id=" + id + ", numempleado=" + numempleado + ", nombre=" + nombre + ", status=" + status 
			+ ", apellido=" + apellido + ", id_users=" + id_users + "]";
	}
	
}
