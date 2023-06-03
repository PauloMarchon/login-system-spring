package com.estudo.LoginSystem.auth;

public record AuthenticationRequest(
        String email,
        String password
) {
}
