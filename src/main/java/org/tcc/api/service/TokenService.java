package org.tcc.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import org.tcc.api.model.Usuario;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    private final String secret = "my-secret";

    private final Algorithm algorithm = Algorithm.HMAC256(secret);

    public String generateToken(Usuario usuario){
        try {

            return JWT.create()
                    .withIssuer("task-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        }catch (Exception ex){
            throw new RuntimeException(ex.getCause());
        }
    }


    public String verigyToken(String token){
        return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
    }
}
