package com.izi.er.controller.dto;

import lombok.Getter;

@Getter
public class ReserveDto { //병원 예약

    private String Hname; //병원이름
    private String username; //환자아이디
    private String Pname; //환자이름
    private String Pnumber; //환자 전화번호
    private String Injury; //부상
}
