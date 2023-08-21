package com.izi.er.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class HospitalDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "hospital")
    private User hospital;

    @Column(nullable=false, length=10)
    private String name;

    @Column(nullable=false)
    private boolean isRunning;
}
