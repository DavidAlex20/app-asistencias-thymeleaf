package com.um.app.models;

import org.springframework.data.annotation.Id;

public class Asigaula {
	@Id
	private int id;
	
	private int id_materias;
	
	private int id_aula;
	
	private int dia;

	private String inicio;

	private String fin;

	public Asigaula() {}
	public Asigaula(int id_materias, int id_aula, int dia, String inicio, String fin) {
		this.id_materias = id_materias;
		this.id_aula = id_aula;
		this.dia = dia;
		this.inicio = inicio;
		this.fin = fin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_materias() {
		return id_materias;
	}

	public void setId_materias(int id_materias) {
		this.id_materias = id_materias;
	}

	public int getId_aula() {
		return id_aula;
	}

	public void setId_aula(int id_aula) {
		this.id_aula = id_aula;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "Asigaula [id=" + id + ", id_materias=" + id_materias + ", id_aula=" + id_aula + ", dia=" + dia + ", inicio=" + inicio + ", fin=" + fin +"]";
	}
}
