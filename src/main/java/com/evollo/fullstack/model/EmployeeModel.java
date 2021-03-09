package com.evollo.fullstack.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
public class EmployeeModel implements Serializable {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(example = "Fulano")
    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ApiModelProperty(example = "Insira seu email real")
    @Column(nullable = false)
    @Email
    private String email;

    @ApiModelProperty(example = "111.555.444-33")
    @Column(nullable = false)
    @NotBlank
    private String cpf;

    @ApiModelProperty(example = "Dev SR")
    @Column(nullable = false)
    private String jobRole;


    @ApiModelProperty(example = "8000")
    @Column(nullable = false)
    private Float salary;

    @ApiModelProperty(example = "ADMIN")
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
