package com.izi.er.model;

import lombok.*;
import javax.persistence.*;
import com.izi.er.model.type.RoleType;
import com.izi.er.model.type.UserType;

@Data
@Table(schema = "er_db",name = "user")
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, length=10, unique=true)
    private String username;

    @Column(nullable=false, length=100)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Enumerated(EnumType.STRING)
    private UserType type;

}
