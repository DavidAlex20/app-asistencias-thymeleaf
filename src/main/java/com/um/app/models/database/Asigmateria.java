package com.um.app.models.database;

import org.springframework.data.annotation.Id;

public class Asigmateria {
	@Id
	private int id;
	
	private int id_maestro;
	
	private int id_materia;
	
	private String fecha_inicio;
	
	private String fecha_fin;

	public Asigmateria(){}
	public Asigmateria(int id_maestro, int id_materia, String fecha_inicio, String fecha_fin) {
		this.id_maestro = id_maestro;
		this.id_materia = id_materia;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_maestro() {
		return id_maestro;
	}

	public void setId_maestro(int id_maestro) {
		this.id_maestro = id_maestro;
	}

	public int getId_materia() {
		return id_materia;
	}

	public void setId_materia(int id_materia) {
		this.id_materia = id_materia;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	@Override
	public String toString() {
		return "Asigmateria [id=" + id + ", id_maestro=" + id_maestro + ", id_materia=" + id_materia + ", fecha_inicio="
				+ fecha_inicio + ", fecha_fin=" + fecha_fin + "]";
	}
}
