package com.izi.er.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "hospital")
    private User hospital;

    @ManyToOne
    @JoinColumn(name = "ambulance")
    private User ambulance;

    @Column(nullable=true, length=20)
    private String patientName;

    @Column(nullable=false)
    private String injury;

    @ColumnDefault("0")
    private boolean approve;

    @ColumnDefault("0")
    private boolean admission;
}
