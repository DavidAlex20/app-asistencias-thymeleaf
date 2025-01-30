package com.um.app.models.dto;

import org.springframework.data.annotation.Id;

public class DetailedReportes {
	@Id
	private int id;
	
	private String numempleado;

    private String nombre;

	private String apellido;

	private String iniciosemana;

	private String finsemana;
	
	private int horaspuntuales;
	
	private int horasimpuntuales;
	
	private int inasistencias;

	public DetailedReportes(){}
	public DetailedReportes(String numempleado, String nombre, String apellido, String iniciosemana, String finsemana, int horaspuntuales, int horasimpuntuales,
			int inasistencias) {
		this.numempleado = numempleado; 
        this.nombre = nombre;
        this.apellido = apellido;
		this.iniciosemana = iniciosemana;
		this.finsemana = finsemana;
		this.horaspuntuales = horaspuntuales;
		this.horasimpuntuales = horasimpuntuales;
		this.inasistencias = inasistencias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getNumempleado() {
        return this.numempleado;
    }

    public void setNumempleado(String numempleado) {
        this.numempleado = numempleado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


	public String getIniciosemana() {
		return iniciosemana;
	}

	public void setIniciosemana(String iniciosemana) {
		this.iniciosemana = iniciosemana;
	}

	public String getFinsemana() {
		return finsemana;
	}

	public void setFinsemana(String finsemana) {
		this.finsemana = finsemana;
	}

	public int getHoraspuntuales() {
		return horaspuntuales;
	}

	public void setHoraspuntuales(int horaspuntuales) {
		this.horaspuntuales = horaspuntuales;
	}

	public int getHorasimpuntuales() {
		return horasimpuntuales;
	}

	public void setHorasimpuntuales(int horasimpuntuales) {
		this.horasimpuntuales = horasimpuntuales;
	}

	public int getInasistencias() {
		return inasistencias;
	}

	public void setInasistencias(int inasistencias) {
		this.inasistencias = inasistencias;
	}
    @Override
    public String toString() {
        return "DetailedReportes [id=" + id + ", numempleado=" + numempleado + ", nombre=" + nombre + ", apellido="
                + apellido + ", iniciosemana=" + iniciosemana + ", finsemana=" + finsemana + ", horaspuntuales="
                + horaspuntuales + ", horasimpuntuales=" + horasimpuntuales + ", inasistencias=" + inasistencias
                + "]";
    }

}
