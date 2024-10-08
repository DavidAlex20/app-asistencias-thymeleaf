package com.um.app.models;

import org.springframework.data.annotation.Id;

public class Aula {
	@Id
	private int id;
	
	private int numaula;
	
	private String alias;
	
	private String edificio;
	
	private String geolocal;
	
	private boolean disponible;

	public Aula(){}
	public Aula(int numaula, String alias, String edificio, String geolocal, boolean disponible) {
		this.numaula = numaula;
		this.alias = alias;
		this.edificio = edificio;
		this.geolocal = geolocal;
		this.disponible = disponible;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumaula() {
		return numaula;
	}

	public void setNumaula(int numaula) {
		this.numaula = numaula;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getGeolocal() {
		return geolocal;
	}

	public void setGeolocal(String geolocal) {
		this.geolocal = geolocal;
	}

	public boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		return "Aula [id=" + id + ", numaula=" + numaula + ", alias=" + alias + ", edificio=" + edificio + ", geolocal="
				+ geolocal + ", disponible=" + disponible + "]";
	}

}
