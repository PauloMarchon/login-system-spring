package com.estudo.LoginSystem.appuser;

import com.estudo.LoginSystem.appuser.payload.AppUserDto;
import com.estudo.LoginSystem.appuser.payload.AppUserRegistrationRequest;
import com.estudo.LoginSystem.appuser.role.ERole;
import com.estudo.LoginSystem.appuser.role.Role;
import com.estudo.LoginSystem.appuser.role.RoleRepository;
import com.estudo.LoginSystem.exception.DuplicateResourceException;
import com.estudo.LoginSystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppUserService {
    private final AppUserDao appUserDao;
    private final AppUserDtoMapper appUserDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AppUserService(@Qualifier("jpa")AppUserDao appUserDao,
                          AppUserDtoMapper appUserDtoMapper,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository) {
        this.appUserDao = appUserDao;
        this.appUserDtoMapper = appUserDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<AppUserDto> getAllAppUsers() {
        return appUserDao.selectAllAppUsers()
                .stream()
                .map(appUserDtoMapper)
                .collect(Collectors.toList());
    }

    public AppUserDto getAppUserById(Integer appUserId) {
        return appUserDao.selectAppUserById(appUserId)
                .map(appUserDtoMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "appuser with id [%s] not found".formatted(appUserId)
                ));
    }

    public void registerAppUser(AppUserRegistrationRequest appUserRegistrationRequest){

        checkIfEmailAlreadyRegistered(
                appUserRegistrationRequest.email());

        checkIfUsernameIsAlreadyTaken(
                appUserRegistrationRequest.username());

        AppUser appUser = new AppUser(
                appUserRegistrationRequest.username(),
                appUserRegistrationRequest.email(),
                passwordEncoder.encode(appUserRegistrationRequest.password())
        );
        appUser.setRoles(assignDefaultRole());

        appUserDao.registerAppUser(appUser);
    }

    private void checkIfEmailAlreadyRegistered(String email){
        if(appUserDao.existsAppUserWithEmail(email)) {
            throw new DuplicateResourceException(
                    "email already registered"
            );
        }
    }
    private void checkIfUsernameIsAlreadyTaken(String username){
        if(appUserDao.existsAppUserWithUsername(username)) {
            throw new DuplicateResourceException(
                    "username already taken"
            );
        }
    }
    private Set<Role> assignDefaultRole(){
        Role userRole = roleRepository.findRoleByAuthority(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Error: role is not found!"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        return roles;
    }

    public void deleteAppUserById(Integer appUserId) {
        checkIfAppUserExistsOrThrow(appUserId);
        appUserDao.deleteAppUserById(appUserId);
    }

    private void checkIfAppUserExistsOrThrow(Integer appUserId) {
        if (!appUserDao.existsAppUserById(appUserId)) {
            throw new ResourceNotFoundException(
                    "appuser with id [%s] not found".formatted(appUserId)
            );
        }
    }

}
