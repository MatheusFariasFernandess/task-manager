package org.tcc.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private final String secret = "hmasca134a";
    private final Algorithm algorithm = Algorithm.HMAC256(secret);


    public String createToken(UserDetails userDetails){
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("tcc-api")
                .withExpiresAt(LocalDateTime.now().plusHours(9999999).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);
    }
    public String verifyToken(String token){
        return JWT.require(algorithm)
                .withIssuer("tcc-api")
                .build()
                .verify(token)
                .getSubject();
    }
}
