package com.zekiyetekin.surveyhub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    private String job;
    private Integer age;

    @Column(unique = true)
    private String mail;
    private String password;
    private  boolean isActive;

}
