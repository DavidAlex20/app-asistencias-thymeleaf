package com.um.app.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsCustom implements UserDetails {

    public int id;
    public String username;
    public String password;
    public String role;
    public String numempleado;
    public String nombre;
    public String apellido;
    public String status;


    public UserDetailsCustom(int id, String username, String password, String role, String numempleado, String nombre, String apellido, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.numempleado = numempleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getNumempleado() {
        return this.numempleado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getStatus() {
        return this.status;
    }

    public int getId() {
        return this.id;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getAuthorities() + "'" +
            ", numempleado='" + getNumempleado() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

    
}
