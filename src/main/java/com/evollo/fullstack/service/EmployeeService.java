package com.evollo.fullstack.service;

import com.evollo.fullstack.exception.EmployeeNotFoundException;
import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeModel> getAll() {
        return employeeRepository.findAll();
    }

    public EmployeeModel getById(Long id) throws EmployeeNotFoundException {
        verifyIfEmployeeExists(id);
        return employeeRepository.findByid(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public EmployeeModel save(EmployeeModel employeeModel) {
        return employeeRepository.save(employeeModel);
    }

    @Transactional(rollbackFor = Exception.class)
    public EmployeeModel update(Long id, EmployeeModel newEmployeeModel) throws EmployeeNotFoundException {

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
        return employeeRepository.save(oldEmployeeModel);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) throws EmployeeNotFoundException {
        verifyIfEmployeeExists(id);
        EmployeeModel employee = employeeRepository.findByid(id);
        employeeRepository.delete(employee);
    }

    private void verifyIfEmployeeExists(Long id) throws EmployeeNotFoundException {
        EmployeeModel employee = employeeRepository.findByid(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee ID Not Found!");
        }
    }

}