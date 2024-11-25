package com.um.app.models.database;


import org.springframework.data.annotation.Id;

public class Reporte {
	@Id
	private int id;
	
	private int id_maestro;
	
	private String semana_inicio;

	private String semana_fin;
	
	private int horas_puntuales;
	
	private int horas_impuntuales;
	
	private int inasistencias;

	public Reporte(){}
	public Reporte(int id_maestro, String semana_inicio, String semana_fin, int horas_puntuales, int horas_impuntuales,
			int inasistencias) {
		this.id_maestro = id_maestro;
		this.semana_inicio = semana_inicio;
		this.semana_fin = semana_fin;
		this.horas_puntuales = horas_puntuales;
		this.horas_impuntuales = horas_impuntuales;
		this.inasistencias = inasistencias;
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

	public String getSemana_inicio() {
		return semana_inicio;
	}

	public void setSemana_inicio(String semana_inicio) {
		this.semana_inicio = semana_inicio;
	}

	public String getSemana_fin() {
		return semana_fin;
	}

	public void setSemana_fin(String semana_fin) {
		this.semana_fin = semana_fin;
	}

	public int getHoras_puntuales() {
		return horas_puntuales;
	}

	public void setHoras_puntuales(int horas_puntuales) {
		this.horas_puntuales = horas_puntuales;
	}

	public int getHoras_impuntuales() {
		return horas_impuntuales;
	}

	public void setHoras_impuntuales(int horas_impuntuales) {
		this.horas_impuntuales = horas_impuntuales;
	}

	public int getInasistencias() {
		return inasistencias;
	}

	public void setInasistencias(int inasistencias) {
		this.inasistencias = inasistencias;
	}

	@Override
	public String toString() {
		return "Reporte [id=" + id + ", id_maestro=" + id_maestro + ", semana_inicio=" + semana_inicio + ", semana_fin="
				+ semana_fin + ", horas_puntuales=" + horas_puntuales + ", horas_impuntuales=" + horas_impuntuales
				+ ", inasistencias=" + inasistencias + "]";
	}
	
}
