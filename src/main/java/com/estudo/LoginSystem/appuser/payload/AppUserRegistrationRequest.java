package com.estudo.LoginSystem.appuser.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AppUserRegistrationRequest(
        @NotBlank @Size(max = 20) String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String password
) {
}
