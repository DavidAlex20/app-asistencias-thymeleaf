package com.um.app.models.database;

import org.springframework.data.annotation.Id;

public class Materias {
	@Id
	private int id;
	
	private String nombre;
	
	private int frecuencia;

	private int semestre;

	public Materias(){}
	public Materias(String nombre, int frecuencia, int semestre) {
		this.nombre = nombre;
		this.frecuencia = frecuencia;
		this.semestre = semestre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	@Override
	public String toString() {
		return "Materias [id=" + id + ", nombre=" + nombre + ", frecuencia=" + frecuencia + ", semestre=" + semestre
				+ "]";
	}
	
}
