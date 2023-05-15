package com.example.gateway.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtService {
    public static final String SECRET_KEY = "my_secret_key";

    public String generate(String username) {
        return JWT.create()
                .withIssuer(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String getUserName(String jwt) {
        return JWT.decode(jwt).getIssuer().toString();
    }

    public boolean deccodeJwt(String jwt) {
        Boolean result = false;
        JWTVerifier jwtVerification = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        try {
            DecodedJWT decodedJWT = jwtVerification.verify(jwt);
            if(decodedJWT != null){
                result = true;
            }
        } catch (TokenExpiredException ex) {
            log.warn("Token not validated - {}", jwt);
            throw new RuntimeException("un authorized access to application");
        } catch (JWTVerificationException ex) {
            log.warn("token not validated {}", jwt);
            throw new RuntimeException("un authorized access to application");
        }
        return result;
    }
}
