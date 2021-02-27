package com.evollo.fullstack.service;

import com.evollo.fullstack.exception.EmployeeNotFoundException;
import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeModel> getAll() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<EmployeeModel> getById(Long id) throws EmployeeNotFoundException {
        verifyIfEmployeeExists(id);
        EmployeeModel employee = employeeRepository.findByid(id);
        return new ResponseEntity<EmployeeModel>(employee, HttpStatus.OK);
    };

    public ResponseEntity<EmployeeModel> save(EmployeeModel employeeModel) {
        return new ResponseEntity<EmployeeModel>(employeeRepository.save(employeeModel), HttpStatus.CREATED) ;
    };

    public ResponseEntity<EmployeeModel> update(Long id, EmployeeModel newEmployeeModel) throws EmployeeNotFoundException {

        verifyIfEmployeeExists(id);
        EmployeeModel oldEmployeeModel = employeeRepository.findByid(id);

        if (newEmployeeModel.getName() != null) {
            oldEmployeeModel.setName(newEmployeeModel.getName());
        }

        if (newEmployeeModel.getEmail() != null) {
            oldEmployeeModel.setEmail(newEmployeeModel.getEmail());
        }

        if (newEmployeeModel.getCpf() != null) {
            oldEmployeeModel.setCpf(newEmployeeModel.getCpf());
        }

        if (newEmployeeModel.getJobRole() != null) {
            oldEmployeeModel.setJobRole(newEmployeeModel.getJobRole());
        }

        if (newEmployeeModel.getSalary() != null) {
            oldEmployeeModel.setSalary(newEmployeeModel.getSalary());
        }

        //update the old employee
        return new ResponseEntity<EmployeeModel>(employeeRepository.save(oldEmployeeModel), HttpStatus.OK);
    };

    public ResponseEntity<EmployeeModel> delete(Long id) throws EmployeeNotFoundException {
        verifyIfEmployeeExists(id);
        EmployeeModel employee = employeeRepository.findByid(id);
        employeeRepository.delete(employee);
        return new ResponseEntity<EmployeeModel>(HttpStatus.NO_CONTENT);
    }

    private void verifyIfEmployeeExists(Long id) throws EmployeeNotFoundException {
        EmployeeModel employee = employeeRepository.findByid(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee ID Not Found!");
        }
    }

}