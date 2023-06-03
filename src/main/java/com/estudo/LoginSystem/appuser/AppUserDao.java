package com.estudo.LoginSystem.appuser;

import java.util.List;
import java.util.Optional;

public interface AppUserDao {
    List<AppUser> selectAllAppUsers();
    Optional<AppUser> selectAppUserById(Integer appUserId);
    void registerAppUser(AppUser appUser);
    void updateAppUser(AppUser appUser);
    void deleteAppUserById(Integer appUserId);
    Optional<AppUser> selectAppUserByUsername(String username);
    Optional<AppUser> selectAppUserByEmail(String email);
    boolean existsAppUserById(Integer id);
    boolean existsAppUserWithUsername(String username);
    boolean existsAppUserWithEmail(String email);
}
