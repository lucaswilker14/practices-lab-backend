package com.evollo.fullstack.controller;

import com.evollo.fullstack.exception.EmployeeAlreadyRegisteredException;
import com.evollo.fullstack.exception.EmployeeNotFoundException;
import com.evollo.fullstack.exception.RoleNotSetException;
import com.evollo.fullstack.exception.UserAlreadyTakenException;
import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> get(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeModel> post(@Valid @RequestBody EmployeeModel employeeModel)
            throws EmployeeAlreadyRegisteredException, UserAlreadyTakenException, RoleNotSetException {
        return new ResponseEntity<>(employeeService.save(employeeModel), HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> put(@PathVariable("id") Long id,
                                             @Valid @RequestBody EmployeeModel newEmployeeModel)
            throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.update(id, newEmployeeModel));
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> patch(@PathVariable("id") Long id,
                                               @RequestBody EmployeeModel newEmployeeModel)
            throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.update(id, newEmployeeModel));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> delete(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
