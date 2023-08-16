package com.izi.er.config.jwt;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtTokenException extends AuthenticationException {
    public InvalidJwtTokenException(String errorMessage) {
        super(errorMessage);
    }
}
