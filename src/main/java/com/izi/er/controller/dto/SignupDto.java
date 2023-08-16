package com.izi.er.controller.dto;

import com.izi.er.model.type.UserType;
import lombok.Getter;

@Getter
public class SignupDto {
    private String username;
    private String password;
    private UserType userType;
}
