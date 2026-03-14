package com.lunarvoid.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lunarvoid.entities.User;
import com.lunarvoid.services.exceptions.TokenException;

@Service
public class TokenService {
    
    @Value("${token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("JWT-APP").withSubject(user.getUsername()).withExpiresAt(Instant.now().plusSeconds(7200)).sign(algorithm);
            return token;
        }catch(JWTCreationException e){
            throw new TokenException("falha ao gerar token");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("JWT-APP").build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            throw new TokenException("token invalido");
        }
    }
}
