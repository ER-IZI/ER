package com.izi.er.controller.dto;

import com.izi.er.model.type.RoleType;
import lombok.Getter;

@Getter
public class SignupDto {
    private String username;
    private String password;
    private RoleType role;
    private String name;
    private String telephone;
    private String address;
    private double latitude;
    private double longitude;
    private int numberOfRoom;
}
