package com.um.app.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsCustom implements UserDetails {

    private UsersMaestro user;

    public UserDetailsCustom(UsersMaestro user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getNombre() {
        return user.getNombre();
    }

    public String getApellido() {
        return user.getApellido();
    }

    public String getNumempleado() {
        return user.getNumempleado();
    }

    public String getStatus() {
        return user.getStatus();
    }
    
}
