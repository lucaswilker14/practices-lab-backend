package com.evollo.fullstack.controller;

import com.evollo.fullstack.model.UserModel;
import com.evollo.fullstack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserModel userModel) {
        return ResponseEntity.ok(userService.update(userModel));
    }
}
