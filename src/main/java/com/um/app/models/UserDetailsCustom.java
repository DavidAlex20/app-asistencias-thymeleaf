package com.um.app.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsCustom implements UserDetails {

    private int id;
    private String username;
    private String password;
    private String role;
    private String numempleado;
    private String nombre;
    private String apellido;
    private String status;


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

    public String getEmpleado() {
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
    
}
