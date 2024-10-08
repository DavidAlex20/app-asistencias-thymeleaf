package com.um.app.models;

import org.springframework.data.annotation.Id;

public class Maestros {
	@Id
	private int id;
	
	private String numempleado;
	
	private String nombre;
	
	private String status;

	public Maestros(){}
	public Maestros(String numempleado, String nombre, String status) {
		this.numempleado = numempleado;
		this.nombre = nombre;
		this.status = status;
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
	
	@Override
	public String toString() {
		return "Maestros [id=" + id + ", numempleado=" + numempleado + ", nombre=" + nombre + ", status=" + status
				+ "]";
	}
	
}
