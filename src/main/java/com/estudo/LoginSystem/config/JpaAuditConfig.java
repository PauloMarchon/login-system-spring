package com.estudo.LoginSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated() || isAnonymous(
                    authentication)) {
                return Optional.empty();
            }

            UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication.getPrincipal();
            String email = (String) userToken.getPrincipal();

            return Optional.ofNullable(email);
        };
    }

    private boolean isAnonymous(Authentication authentication) {
        return authentication instanceof AnonymousAuthenticationToken;
    }
}
