package com.estudo.LoginSystem.appuser;

import com.estudo.LoginSystem.appuser.payload.AppUserDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AppUserDtoMapper implements Function<AppUser, AppUserDto> {
    @Override
    public AppUserDto apply(AppUser appUser) {
        return new AppUserDto(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getAuthorities()
        );
    }
}
