package com.um.app.models.dto;

public class Horario {
    private int id, numaula, dia;
    private String nombre, inicio, fin;

    public Horario(){}
    public Horario(int id, String nombre, int numaula, int dia, String inicio, String fin) {
        this.id = id;
        this.numaula = numaula;
        this.dia = dia;
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumaula() {
        return this.numaula;
    }

    public void setNumaula(int numaula) {
        this.numaula = numaula;
    }

    public int getDia() {
        return this.dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInicio() {
        return this.inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return this.fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", numaula='" + getNumaula() + "'" +
            ", dia='" + getDia() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", inicio='" + getInicio() + "'" +
            ", fin='" + getFin() + "'" +
            "}";
    }
}
