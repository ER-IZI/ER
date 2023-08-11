package com.izi.er.controller.dto;

import lombok.Getter;

@Getter
public class InjuryDto<K, R> {
    private K kind;
    private R risk;
}
