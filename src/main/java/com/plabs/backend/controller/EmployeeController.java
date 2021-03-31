package com.plabs.backend.controller;

import com.plabs.backend.exception.EmployeeAlreadyRegisteredException;
import com.plabs.backend.exception.EmployeeNotFoundException;
import com.plabs.backend.exception.RoleNotSetException;
import com.plabs.backend.exception.UserAlreadyTakenException;
import com.plabs.backend.model.EmployeeModel;
import com.plabs.backend.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Modulo de criação/edição/busca/remoção de funcionários", tags = "2 - Funcionários")
@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @ApiOperation(value = "Retorna todas os funcionários cadastrados!")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }


    @ApiOperation(value = "Buscar Funcionário")
    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @ApiOperation(value = "Cadastrar Funcionário")
    @PostMapping(value = "/employee")
    public ResponseEntity<EmployeeModel> post(@Valid @RequestBody EmployeeModel employeeModel)
            throws EmployeeAlreadyRegisteredException, UserAlreadyTakenException, RoleNotSetException {
        return new ResponseEntity<>(employeeService.save(employeeModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar Funcionário Completo")
    @PutMapping(value = "/employee/{id}")
    public ResponseEntity<EmployeeModel> put(@PathVariable("id") Long id,
                                             @Valid @RequestBody EmployeeModel newEmployeeModel)
            throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.update(id, newEmployeeModel));
    }

    @ApiOperation(value = "Atualizar parte do recurso de um Funcionário")
    @PatchMapping(value = "/employee/{id}")
    public ResponseEntity<EmployeeModel> patch(@PathVariable("id") Long id,
                                               @RequestBody EmployeeModel newEmployeeModel)
            throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.update(id, newEmployeeModel));
    }

    @ApiOperation(value = "Deletar Funcionário")
    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<EmployeeModel> delete(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
