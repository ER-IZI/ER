package com.izi.er.model;

import lombok.*;
import javax.persistence.*;
import com.izi.er.model.type.RoleType;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false, length=10, unique=true)
    private String username;
    @Column(nullable=false, length=100, unique=false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=10)
    private RoleType role;
    @Column(nullable=false, length=20, unique=false)
    private String name;
    @Column(nullable=false, length=20, unique=true)
    private String telephone;
    @Column(nullable=false, length=30, unique=true)
    private String address;
    @Column(nullable=true)
    private double latitude;
    @Column(nullable=true)
    private double longitude;
}
