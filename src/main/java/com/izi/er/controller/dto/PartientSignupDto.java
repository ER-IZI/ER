package com.izi.er.controller.dto;

import com.izi.er.model.type.UserType;
import lombok.Getter;

@Getter
public class PartientSignupDto {
    private String username; // 아이디
    private String password; // 패스워드
    private String PName; // 환자 이름
    private String Pnumber; // 환자 전화 번호
    private UserType type; // 유저 타입

}
