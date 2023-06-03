package com.estudo.LoginSystem.appuser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class AppUserJPADataAccessService implements AppUserDao{

    private final AppUserRepository appUserRepository;

    public AppUserJPADataAccessService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<AppUser> selectAllAppUsers() {
        Page<AppUser> page =appUserRepository.findAll(Pageable.ofSize(10));
        return page.getContent();
    }

    @Override
    public Optional<AppUser> selectAppUserById(Integer appUserId) {
        return appUserRepository.findById(appUserId);
    }

    @Override
    public void registerAppUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public void updateAppUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public void deleteAppUserById(Integer appUserId) {
        appUserRepository.deleteById(appUserId);
    }

    @Override
    public Optional<AppUser> selectAppUserByUsername(String username) { return appUserRepository.findAppUserByUsername(username);}

    @Override
    public Optional<AppUser> selectAppUserByEmail(String email) {
        return appUserRepository.findAppUserByEmail(email);
    }

    @Override
    public boolean existsAppUserById(Integer id) { return appUserRepository.existsAppUserById(id); }
    @Override
    public boolean existsAppUserWithUsername(String username) {return appUserRepository.existsAppUserByUsername(username);}

    @Override
    public boolean existsAppUserWithEmail(String email) {
        return appUserRepository.existsAppUserByEmail(email);
    }
}
