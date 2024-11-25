package com.um.app.models.dto;

import org.springframework.data.annotation.Id;

public class MateriasAsignadas {
    @Id
    private int id;
    private String nombre;
    private String fecha_inicio;
    private String fecha_fin;

    public MateriasAsignadas() {}
    public MateriasAsignadas(String nombre, String fecha_inicio, String fecha_fin) {
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
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
        return "MateriasAsignadas [id=" + id + ", nombre=" + nombre + ", fecha_inicio=" + fecha_inicio + ", fecha_fin="
                + fecha_fin + "]";
    }
}
