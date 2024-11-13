package com.um.app.models;

import org.springframework.data.annotation.Id;

public class UsersMaestro {
    @Id
    private int id;
    private String username;
    private String password;
    private String role;
    private String numempleado;
    private String nombre;
    private String apellido;
    private String status;

    public UsersMaestro() {}
    public UsersMaestro(int id, String username, String password, String role, String numempleado, String nombre, String apellido, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.numempleado = numempleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getRole() + "'" +
            ", numempleado='" + getNumempleado() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

}
