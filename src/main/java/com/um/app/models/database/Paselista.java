package com.um.app.models.database;

import org.springframework.data.annotation.Id;

public class Paselista {
	@Id
	private int id;
	private int id_asigmateria;
	private String geolocal;
	private String hora_entrada;
	private String hora_salida;
	private String fecha;

	public Paselista(){}
	public Paselista(int id_asigmateria, String geolocal, String hora_entrada, String hora_salida, String fecha) {
		this.id_asigmateria = id_asigmateria;
		this.geolocal = geolocal;
		this.hora_entrada = hora_entrada;
		this.hora_salida = hora_salida;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(String hora_entrada) {
		this.hora_entrada = hora_entrada;
	}

	public String getHora_salida() {
		return hora_salida;
	}

	public void setHora_salida(String hora_salida) {
		this.hora_salida = hora_salida;
	}

	public int getId_asigmateria() {
		return id_asigmateria;
	}

	public void setId_asigmateria(int id_asigmateria) {
		this.id_asigmateria = id_asigmateria;
	}

	public String getGeolocal() {
		return geolocal;
	}

	public void setGeolocal(String geolocal) {
		this.geolocal = geolocal;
	}

	public String getFecha(){
		return this.fecha;
	}

	public void setFecha(String fecha) {

	}
	
	@Override
	public String toString() {
		return "Paselista [id=" + id + ", hora_entrada=" + hora_entrada + ", hora_salida=" + hora_salida
				+ ", id_asigmateria=" + id_asigmateria + ", geolocal=" + geolocal + "]";
	}
	
}
