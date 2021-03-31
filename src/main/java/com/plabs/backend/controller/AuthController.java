package com.plabs.backend.controller;

import com.plabs.backend.payload.LoginRequestModel;
import com.plabs.backend.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@Api(value="Login", tags = "1 - Login")
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value="Realiza Login no Sistema", notes="Use Admin Default -> Username: Admin; Password: 123 ")
    @PostMapping(value = "/login", produces="application/json")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestModel loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

}
