package com.plabs.backend.service;

import com.plabs.backend.exception.EmployeeNotFoundException;
import com.plabs.backend.model.CompanyModel;
import com.plabs.backend.model.EmployeeModel;
import com.plabs.backend.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<CompanyModel> getCompanies() {
        return companyRepository.findAll();
    }

    @Transactional
    public CompanyModel createNewCompany(CompanyModel companyModel) throws EmployeeNotFoundException {
        verifyIfCompanyExists(companyModel.getCnpj());
        companyModel.setCompanyName(companyModel.getCompanyName().toUpperCase(Locale.ROOT));
        return companyRepository.save(companyModel);
    }

    public ResponseEntity<Object> getById(Long id) throws EmployeeNotFoundException {
        verifyIfCompanyExistsById(id);
        CompanyModel companyModel = companyRepository.findByid(id);
        return new ResponseEntity<>(companyModel, HttpStatus.OK);
    }

    public void addNewEmployee(EmployeeModel employeeModel) {
        String companyCnpj = employeeModel.getCompany().getCnpj();
        CompanyModel companyModel = companyRepository.findByCnpj(companyCnpj);
        companyModel.getEmployees().add(employeeModel);
        companyRepository.save(companyModel);
    }

    private void verifyIfCompanyExistsById(Long id) throws EmployeeNotFoundException {
        boolean existsById = companyRepository.existsById(id);
        if (!existsById) {
            throw new EmployeeNotFoundException("Company ID Not Found!");
        }
    }

    private void verifyIfCompanyExists(String cnpj) throws EmployeeNotFoundException {
        boolean existsCompany = companyRepository.existsByCnpj(cnpj);
        if (existsCompany) {
            throw new EmployeeNotFoundException("Company Already Registered!");
        }
    }

    public void removeCompanyUser(EmployeeModel employeeModel) {
        CompanyModel company = this.companyRepository.findByid(employeeModel.getCompany().getId());
        company.getEmployees().remove(employeeModel);
    }
}
