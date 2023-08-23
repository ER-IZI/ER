package com.izi.er.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(schema = "er_db",name = "ambulance")
public class Ambulance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Aid")
    private long Aid; // 환자 코드

    @OneToOne()
    private User user;


    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReserveList> reserveListDto; // 예약 리스트

    @Column(name = "Aname")
    private String OName; // 환자 이름

    @Column(name = "Anumber")
    private String Pnumber; // 환자 전화 번호
}
