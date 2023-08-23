package com.izi.er.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "er",name = "MedicalDepartment")
public class MedicalDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DID")
    private long DID; // 부서 코드

    @ManyToOne(optional = false)
    @JoinColumn(name = "id")
    private Hospital id; // 병원 코드

    @Column(name = "Department")
    private String Department; // 부서 이름

    @Column(name = "Acheck")
    private boolean Acheck; // 진료가능 여부,   false == 진료 x  , true == 진료 O
}
