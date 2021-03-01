package com.evollo.fullstack.controller;

import com.evollo.fullstack.payload.JwtAuthenticationResponse;
import com.evollo.fullstack.security.JwtTokenProvider;
import com.evollo.fullstack.model.*;
import com.evollo.fullstack.payload.LoginRequestModel;
import com.evollo.fullstack.payload.SignUpRequest;
import com.evollo.fullstack.repository.RoleRepository;
import com.evollo.fullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestModel loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new Exception("Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserModel user = new UserModel(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleModel userRole = roleRepository.findByRole(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new Exception("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/evollo/api/auth/signup/{username}")
//                .buildAndExpand(result.getUsername()).toUri();

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

}
