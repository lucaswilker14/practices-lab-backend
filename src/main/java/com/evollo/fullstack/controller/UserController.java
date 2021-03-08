package com.evollo.fullstack.controller;

import com.evollo.fullstack.exception.FieldInvalidException;
import com.evollo.fullstack.exception.UserAlreadyTakenException;
import com.evollo.fullstack.model.UserModel;
import com.evollo.fullstack.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "4 - Usuário", value = "Criado no momento da criação do funcionário e enviado por email")
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Cadastrar novo Usuário do Sistema")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserModel userModel)
            throws FieldInvalidException {
        return ResponseEntity.ok(userService.update(id, userModel));
    }
}
