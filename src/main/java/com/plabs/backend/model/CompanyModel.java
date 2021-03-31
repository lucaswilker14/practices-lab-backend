package com.plabs.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CompanyModel {

    @ApiModelProperty(example = "2")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(example = "ABCD")
    private String companyName;

    @ApiModelProperty(example = "123456789")
    @Column(unique = true)
    private String cnpj;

    @ApiModelProperty(example = "São Paulo")
    private String city;

    @ApiModelProperty(example = "SP")
    private String state;

    @ApiModelProperty(example = "Rua trelo teste")
    private String address;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeModel> employees = new ArrayList<>();

    public CompanyModel(String companyName, String cnpj, String city, String state, String address) {
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.city = city;
        this.state = state;
        this.address = address;
    }

    public CompanyModel() {

    }
}
