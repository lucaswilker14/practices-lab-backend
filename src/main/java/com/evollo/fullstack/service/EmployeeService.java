package com.evollo.fullstack.service;

import com.evollo.fullstack.exception.EmployeeAlreadyRegisteredException;
import com.evollo.fullstack.exception.EmployeeNotFoundException;
import com.evollo.fullstack.exception.RoleNotSetException;
import com.evollo.fullstack.exception.UserAlreadyTakenException;
import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.model.RoleName;
import com.evollo.fullstack.payload.SignUpRequest;
import com.evollo.fullstack.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;


    public List<EmployeeModel> getAll() {
        return employeeRepository.findAll();
    }

    public EmployeeModel getById(Long id) throws EmployeeNotFoundException {
        verifyIfEmployeeExists(id);
        return employeeRepository.findByid(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public EmployeeModel save(EmployeeModel employeeModel) throws EmployeeAlreadyRegisteredException,
            UserAlreadyTakenException, RoleNotSetException {

        verifyIfCpfExists(employeeModel.getCpf());

        //create common user
        RoleName roleName = (RoleName.ROLE_USER.name().contains(employeeModel.getPermission()))
                ? RoleName.ROLE_USER : RoleName.ROLE_ADMIN;
        SignUpRequest newUser = new SignUpRequest(employeeModel.getName(), "", "", roleName);
        createNewUser(newUser);

        //send credencial email

        //save
        return employeeRepository.save(employeeModel);
    }

    @Transactional(rollbackFor = Exception.class)
    public EmployeeModel update(Long id, EmployeeModel newEmployeeModel) throws EmployeeNotFoundException {

        verifyIfEmployeeExists(id);
        verifyPermissionSelfUpdate(newEmployeeModel.getCpf());

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

    private void verifyPermissionSelfUpdate(String cpf) throws EmployeeNotFoundException {
        EmployeeModel employeeModel = employeeRepository.findByCpf(cpf);
        if (!employeeModel.getCpf().equals(cpf)) {
            throw new EmployeeNotFoundException("Employee ID Not Found!");
        }
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

    private void verifyIfCpfExists(String cpf) throws EmployeeAlreadyRegisteredException {
        Boolean hasCpf = employeeRepository.existsByCpf(cpf);
        if (hasCpf) {
            throw new EmployeeAlreadyRegisteredException("Employee Already Registered!");
        }
    }

    private void createNewUser(SignUpRequest signUpRequest) throws RoleNotSetException, UserAlreadyTakenException {
        userService.createNewUser(signUpRequest);
    }



}