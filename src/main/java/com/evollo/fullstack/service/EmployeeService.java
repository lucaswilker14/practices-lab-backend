package com.evollo.fullstack.service;

import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeModel> getAll() {
        return employeeRepository.findAll();
    }

    public EmployeeModel getByid(Long id) {
        return employeeRepository.findByid(id);
    };

    public EmployeeModel save(EmployeeModel employeeModel) {
        return employeeRepository.save(employeeModel);
    };

    public EmployeeModel update(EmployeeModel oldEmployeeModel, EmployeeModel newEmployeeModel) {
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
        return employeeRepository.save(oldEmployeeModel);
    };

    public String delete(Long id) {
        EmployeeModel employeeModel = employeeRepository.findByid(id);
        employeeRepository.delete(employeeModel);
        return "Deleted Employee";
    }

}