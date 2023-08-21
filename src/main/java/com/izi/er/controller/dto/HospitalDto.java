package com.izi.er.controller.dto;

import lombok.Getter;

@Getter
public class HospitalDto {
    private String username;
    private String password;
    private String name;
    private String telephone;
    private String address;
    private double latitude;
    private double longitude;
    private int numberOfBed;
}
