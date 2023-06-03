package com.estudo.LoginSystem.auth;

import com.estudo.LoginSystem.appuser.AppUser;
import com.estudo.LoginSystem.appuser.AppUserDtoMapper;
import com.estudo.LoginSystem.appuser.payload.AppUserDto;
import com.estudo.LoginSystem.security.jwt.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AppUserDtoMapper appUserDtoMapper;
    private final TokenService tokenService;

    public AuthenticationService(AuthenticationManager authenticationManager, AppUserDtoMapper appUserDtoMapper, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.appUserDtoMapper = appUserDtoMapper;
        this.tokenService = tokenService;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        AppUser principal = (AppUser) auth.getPrincipal();
        AppUserDto response = appUserDtoMapper.apply(principal);
        String token = tokenService.generateJwt(auth);

        return new AuthenticationResponse(response, token);
    }

}
