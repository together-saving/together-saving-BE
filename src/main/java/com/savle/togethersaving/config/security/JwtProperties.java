package com.savle.togethersaving.config.security;

public interface JwtProperties {

    String SECRET = "savleTeam";
    int EXPIRATION_TIME = 864000000; //
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}