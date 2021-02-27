package com.evollo.fullstack.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String jobRole;

    @Column(nullable = false)
    private Float salary;

}
