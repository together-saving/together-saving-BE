package com.savle.togethersaving.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.savle.togethersaving.config.security.JwtProperties;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class JwtTokenTest {

    @Test
    void test() {

        Algorithm AL = Algorithm.HMAC512("test");
        String jwtToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", 1L)
                .withClaim("email", "test@naver.com")
                .sign(AL);

        DecodedJWT decode = JWT.require(AL).build().verify(jwtToken);
    }
}
