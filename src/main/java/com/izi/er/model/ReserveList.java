package com.izi.er.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "er",name = "ReserveList")
public class ReserveList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RID")
    private long RID; // 예약 코드

    @ManyToOne(optional = false)
    @JoinColumn(name = "HID")
    private Hospital HID; // 병원 코드

    @ManyToOne(optional = false)
    @JoinColumn(name = "AID")
    private Ambulance AID; //환자 코드

    @Column(name = "Name")
    private String Name; //환자 이름

    @Column(name = "Approve")
    private boolean Approve; //예약 승인

    @Column(name = "Injury")
    private String Injury; //부상

    @Column(name = "ICheck")
    private boolean ICheck; //입실 체크
}
