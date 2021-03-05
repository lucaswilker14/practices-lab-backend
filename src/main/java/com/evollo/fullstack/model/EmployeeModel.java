package com.evollo.fullstack.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String cpf;

    @Column(nullable = false)
    private String jobRole;

    @Column(nullable = false)
    private Float salary;

    @Column(nullable = false)
    private String permission;

    public EmployeeModel(@NotBlank(message = "Name is mandatory") String name, @Email String email,
                         @NotBlank String cpf, String jobRole, Float salary, String permission) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.jobRole = jobRole;
        this.salary = salary;
        this.permission = permission;
    }

    public EmployeeModel() {

    }
}
