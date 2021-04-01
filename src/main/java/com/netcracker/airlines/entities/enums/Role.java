package com.netcracker.airlines.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return toString();
    }

    @Override
    public String toString(){
        return "ROLE_" + name();
    }
}
