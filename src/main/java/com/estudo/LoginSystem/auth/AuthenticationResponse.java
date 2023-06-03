package com.estudo.LoginSystem.auth;

import com.estudo.LoginSystem.appuser.payload.AppUserDto;

public record AuthenticationResponse(
        AppUserDto appUserDto,
        String token
) {
}
