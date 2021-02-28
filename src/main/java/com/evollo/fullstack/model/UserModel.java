package com.evollo.fullstack.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    private String roles;
}
