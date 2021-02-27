package com.evollo.fullstack.controller;

import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evollo/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<EmployeeModel> getAll() throws Exception {
        return employeeService.getAll();
    }

    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeModel get(@PathVariable("id") Long id) {
        return employeeService.getByid(id);
    };

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeModel post(@RequestBody EmployeeModel employeeModel) {
        return employeeService.save(employeeModel);
    };

    @PutMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeModel put(@PathVariable("id") Long id, @RequestBody EmployeeModel newEmployeeModel) {
        EmployeeModel oldEmployeeModel = employeeService.getByid(id);
        return employeeService.update(oldEmployeeModel, newEmployeeModel);
    };

    @PatchMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeModel patch(@PathVariable("id") Long id, @RequestBody EmployeeModel newEmployeeModel) {
        EmployeeModel oldEmployeeModel = employeeService.getByid(id);
        return employeeService.update(oldEmployeeModel, newEmployeeModel);
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") Long id) {
        return employeeService.delete(id);
    }

}
