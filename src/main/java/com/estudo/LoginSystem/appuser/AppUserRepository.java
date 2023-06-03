package com.estudo.LoginSystem.appuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findAppUserByUsername(String username);
    Optional<AppUser> findAppUserByEmail(String email);
    boolean existsAppUserById(Integer id);
    boolean existsAppUserByEmail(String email);
    boolean existsAppUserByUsername(String username);

}
