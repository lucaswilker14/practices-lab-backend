package com.evollo.fullstack.service;

import com.evollo.fullstack.exception.RoleNotSetException;
import com.evollo.fullstack.exception.UserAlreadyTakenException;
import com.evollo.fullstack.model.RoleModel;
import com.evollo.fullstack.model.RoleName;
import com.evollo.fullstack.model.UserModel;
import com.evollo.fullstack.payload.JwtAuthenticationResponse;
import com.evollo.fullstack.payload.LoginRequestModel;
import com.evollo.fullstack.payload.SignUpRequest;
import com.evollo.fullstack.repository.RoleRepository;
import com.evollo.fullstack.repository.UserRepository;
import com.evollo.fullstack.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;



    public JwtAuthenticationResponse authenticateUser(LoginRequestModel loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }


    public void registerUser(SignUpRequest signUpRequest) throws RoleNotSetException, UserAlreadyTakenException {

        verifyUserExists(signUpRequest);

        // Creating user's account
        UserModel user = new UserModel(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleModel userRole = roleRepository.findByRole(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RoleNotSetException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);
    }

    private void verifyUserExists(SignUpRequest signUpRequest) throws UserAlreadyTakenException {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyTakenException("Username is already taken!");
        }
    }
}
