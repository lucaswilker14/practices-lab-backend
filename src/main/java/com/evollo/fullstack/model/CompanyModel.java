package com.evollo.fullstack.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CompanyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @Column(unique = true)
    private String cnpj;

    private String city;

    private String state;

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
