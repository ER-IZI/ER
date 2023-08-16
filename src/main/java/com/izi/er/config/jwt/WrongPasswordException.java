package com.izi.er.config.jwt;

import org.springframework.security.core.AuthenticationException;

public class WrongPasswordException extends AuthenticationException {
    public WrongPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
