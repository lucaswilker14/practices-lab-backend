package com.evollo.fullstack.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
public class EmployeeModel implements Serializable {

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

    @ManyToOne(fetch = FetchType.EAGER)
    private CompanyModel company;

    public EmployeeModel(@NotBlank(message = "Name is mandatory") String name, @Email String email,
                         @NotBlank String cpf, String jobRole, Float salary, String permission, CompanyModel company) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.jobRole = jobRole;
        this.salary = salary;
        this.permission = permission;
        this.company = company;
    }

    public EmployeeModel() {

    }
}
