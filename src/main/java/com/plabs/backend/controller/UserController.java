package com.plabs.backend.controller;

import com.plabs.backend.exception.FieldInvalidException;
import com.plabs.backend.model.UserModel;
import com.plabs.backend.service.UserService;
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
