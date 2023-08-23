package com.izi.er.controller.dto;

import com.izi.er.model.type.UserType;
import lombok.Getter;

@Getter
public class HospitalSignupDto {
    private String username; // 아이디
    private String password; // 패스워드
    private String Hname; // 병원 이름
    private String Pnumber; // 병원 전화번호
    private UserType type; // 유저 타입
    private LocationDto location; //병원 위치
    private int AER; // 응급실 가용병수 (영어 모르겠음)\
    private int RAER; // 남은 응급실 가용병수 (영어 모르겠음)\
    private int MaxReserver; // 병원 최대 예약 가능 수
}