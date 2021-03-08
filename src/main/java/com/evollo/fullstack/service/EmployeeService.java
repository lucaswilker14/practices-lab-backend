package com.evollo.fullstack.service;

import com.evollo.fullstack.exception.EmployeeAlreadyRegisteredException;
import com.evollo.fullstack.exception.EmployeeNotFoundException;
import com.evollo.fullstack.exception.RoleNotSetException;
import com.evollo.fullstack.exception.UserAlreadyTakenException;
import com.evollo.fullstack.model.CompanyModel;
import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.model.UserModel;
import com.evollo.fullstack.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Log4j2
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;
    private final UserService userService;


    public List<EmployeeModel> getAll() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<Object> getById(Long id) throws EmployeeNotFoundException {
        verifyIfEmployeeExists(id);
        EmployeeModel employee = employeeRepository.findByid(id);
        UserModel user = userService.getUserByName(employee.getName());
        Map<String, Object> userEmployee = new HashMap<>();
        userEmployee.put("employee", employee);
        userEmployee.put("user", user);
        return new ResponseEntity<>(userEmployee, HttpStatus.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    public EmployeeModel save(EmployeeModel employeeModel) throws EmployeeAlreadyRegisteredException,
            UserAlreadyTakenException, RoleNotSetException {

        verifyIfCpfExists(employeeModel.getCpf());
        registeNewUser(employeeModel);
        employeeRepository.save(employeeModel);
        companyService.addNewEmployee(employeeModel);
        return employeeModel;
    }

    @Transactional(rollbackFor = Exception.class)
    public EmployeeModel update(Long id, EmployeeModel newEmployeeModel) throws EmployeeNotFoundException {

        verifyIfEmployeeExists(id);

        EmployeeModel oldEmployeeModel = employeeRepository.findByid(id);

        if (!newEmployeeModel.getName().isBlank() &&
                !sameFields(newEmployeeModel.getName(), oldEmployeeModel.getName())) {
            oldEmployeeModel.setName(newEmployeeModel.getName());
        }

        if (!newEmployeeModel.getEmail().isBlank() &&
                !sameFields(newEmployeeModel.getEmail(), oldEmployeeModel.getEmail())) {
            oldEmployeeModel.setEmail(newEmployeeModel.getEmail());
        }

        if (!newEmployeeModel.getCpf().isBlank() &&
                !sameFields(newEmployeeModel.getCpf(), oldEmployeeModel.getCpf())) {
            oldEmployeeModel.setCpf(newEmployeeModel.getCpf());
        }

        if (!newEmployeeModel.getJobRole().isBlank() &&
                !sameFields(newEmployeeModel.getJobRole(), oldEmployeeModel.getJobRole())) {
            oldEmployeeModel.setJobRole(newEmployeeModel.getJobRole());
        }

        if (!newEmployeeModel.getSalary().isNaN() &&
                !sameSalary(newEmployeeModel.getSalary(), oldEmployeeModel.getSalary())) {
            oldEmployeeModel.setSalary(newEmployeeModel.getSalary());
        }

        //update the old employee
        return employeeRepository.save(oldEmployeeModel);
    }

    private boolean sameFields(String compare1, String compare2) {
        return compare1.equals(compare2);
    }

    private boolean sameSalary(Float salary1, Float salary2) {
        return salary1.equals(salary2);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) throws EmployeeNotFoundException {
        verifyIfEmployeeExists(id);
        EmployeeModel employee = employeeRepository.findByid(id);
        removeCompanyUser(employee);
        employeeRepository.delete(employee);
    }

    private void verifyIfEmployeeExists(Long id) throws EmployeeNotFoundException {
        EmployeeModel employee = employeeRepository.findByid(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee ID Not Found!");
        }
    }

    private void verifyIfCpfExists(String cpf) throws EmployeeAlreadyRegisteredException {
        Boolean hasCpf = employeeRepository.existsByCpf(cpf);
        if (hasCpf) {
            throw new EmployeeAlreadyRegisteredException("Employee Already Registered!");
        }
    }

    private void registeNewUser(EmployeeModel employeeModel) throws RoleNotSetException, UserAlreadyTakenException {
        userService.registeNewUser(employeeModel);
    }

    private void removeCompanyUser(EmployeeModel employeeModel) {
        this.companyService.removeCompanyUser(employeeModel);
    }

}