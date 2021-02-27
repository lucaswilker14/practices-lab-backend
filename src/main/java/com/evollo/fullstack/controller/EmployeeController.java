package com.evollo.fullstack.controller;

import com.evollo.fullstack.exception.EmployeeNotFoundException;
import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evollo/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<EmployeeModel> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> get(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return employeeService.getById(id);
    };

    @PostMapping("/employee")
    public ResponseEntity<EmployeeModel> post(@RequestBody EmployeeModel employeeModel) {
        return employeeService.save(employeeModel);
    };

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> put(@PathVariable("id") Long id,
                                             @RequestBody EmployeeModel newEmployeeModel) throws EmployeeNotFoundException {
        return employeeService.update(id, newEmployeeModel);
    };

    @PatchMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> patch(@PathVariable("id") Long id,
                                               @RequestBody EmployeeModel newEmployeeModel) throws EmployeeNotFoundException {
        return employeeService.update(id, newEmployeeModel);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> delete(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return employeeService.delete(id);
    }

}
