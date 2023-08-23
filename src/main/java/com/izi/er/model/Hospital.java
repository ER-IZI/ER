package com.izi.er.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(schema = "er_db",name = "hospital",indexes = @Index(columnList = "Latitude"))
@NoArgsConstructor
@Setter
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hid" , length = 100)
    private long Hid; //병원 코드


    @OneToOne()
    private User user;
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MedicalDepartment> medicalDepartmentDto;
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReserveList> reserveListDto;

    @Column(name = "Hname")
    private String Hname; // 병원 이름

    @Column(name = "Latitude")
    private double Latitude; // 위도

    @Column(name = "Longtitude")
    private double Longtitude; // 경도

    @Column(name = "Pnumber")
    private String Pnumber; // 병원 전화번호

    @Column(name = "AER")
    private int AER; // 응급실 가용병수 (영어 모르겠음)\

    @Column(name = "RAER")
    private int RAER; // 남은 응급실 가용병수 (영어 모르겠음)\

    @Column(name = "MaxReserver")
    private int MaxReserver; // 병원 최대 예약 가능 수
}
