package com.estudo.LoginSystem.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateJwt(Authentication auth){
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("LoginSystem")
                .issuedAt(Instant.now())
                .subject(auth.getName())
                .claim("roles", getAuthorities(auth))
                .build();

        return  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String getAuthorities(Authentication auth) {
        return auth.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.joining(" "));
    }

}
