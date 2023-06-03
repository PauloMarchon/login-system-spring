package com.estudo.LoginSystem.appuser;

import com.estudo.LoginSystem.appuser.payload.AppUserDto;
import com.estudo.LoginSystem.appuser.payload.AppUserRegistrationRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/appuser")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUserDto> getAppUsers() {
        return appUserService.getAllAppUsers();
    }

    @GetMapping("{appUserId}")
    public AppUserDto getAppUserById(@PathVariable("appUserId") Integer appUserId) {
        return appUserService.getAppUserById(appUserId);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAppUser(@RequestBody @Valid AppUserRegistrationRequest request) {
        appUserService.registerAppUser(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION)
                .build();

    }
}
