package com.izi.er.controller.dto;

import lombok.Getter;

@Getter
public class HospitalInfoDto {
    private int Pnumber; // 패스워드
    private int AER; // 응급실 가용병수 (영어 모르겠음)\
    private int RAER; // 남은 응급실 가용병수 (영어 모르겠음)\
    private int MaxReserver; // 병원 최대 예약 가능 수

}
