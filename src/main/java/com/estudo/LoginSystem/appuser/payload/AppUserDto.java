package com.estudo.LoginSystem.appuser.payload;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record AppUserDto(
        Integer id,
        String username,
        String email,
        Collection<? extends GrantedAuthority> authorities
) {
}
