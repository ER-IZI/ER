package com.izi.er.model;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "er_db",name = "ordinary")
public class Ordinary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Oid")
    private long Oid; // 환자 코드

    @OneToOne()
    private User user;


    @Column(name = "Oname")
    private String Oname; // 환자 이름

    @Column(name = "Onumber")
    private String Onumber; // 환자 전화 번호

}
