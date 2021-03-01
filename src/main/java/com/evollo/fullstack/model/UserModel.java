package com.evollo.fullstack.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleModel> roles = new HashSet<>();

    public UserModel(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

}
