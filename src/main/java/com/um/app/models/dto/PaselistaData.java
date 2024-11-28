package com.um.app.models.dto;

public class PaselistaData {
    private int id;
    private String geolocal;
    private String entrada;
    private String salida;
    private String fecha;
    private boolean falta;
    private String nombre;


    public PaselistaData(int id, String geolocal, String entrada, String salida, String fecha, boolean falta, String nombre) {
        this.id = id;
        this.geolocal = geolocal;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
        this.falta = falta;
        this.nombre = nombre;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGeolocal() {
        return this.geolocal;
    }

    public void setGeolocal(String geolocal) {
        this.geolocal = geolocal;
    }

    public String getEntrada() {
        return this.entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return this.salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isFalta() {
        return this.falta;
    }

    public boolean getFalta() {
        return this.falta;
    }

    public void setFalta(boolean falta) {
        this.falta = falta;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", geolocal='" + getGeolocal() + "'" +
            ", entrada='" + getEntrada() + "'" +
            ", salida='" + getSalida() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", falta='" + isFalta() + "'" +
            ", nombre='" + getNombre() + "'" +
            "}";
    }

}
