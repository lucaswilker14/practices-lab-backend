package com.evollo.fullstack.controller;

import com.evollo.fullstack.exception.EmployeeNotFoundException;
import com.evollo.fullstack.model.CompanyModel;
import com.evollo.fullstack.service.CompanyService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Empresa", tags = "2 - Empresa")
@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @ApiOperation(value = "Recupera todas as empresas cadastradas", ignoreJsonView = true)
    @GetMapping("/company")
    public ResponseEntity<List<CompanyModel>> getCompanies() {
        return ResponseEntity.ok(companyService.getCompanies());
    }

    @ApiOperation(value = "Criar nova empresa")
    @PostMapping("/company")
    public ResponseEntity<CompanyModel> createNewCompany(@Valid @RequestBody CompanyModel companyModel)
            throws EmployeeNotFoundException {
        return new ResponseEntity<>(companyService.createNewCompany(companyModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Recupera empresa pelo ID")
    @GetMapping("/company/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(companyService.getById(id));
    }

}
