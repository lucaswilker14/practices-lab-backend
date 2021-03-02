package com.evollo.fullstack.service;

import com.evollo.fullstack.exception.RoleNotSetException;
import com.evollo.fullstack.exception.UserAlreadyTakenException;
import com.evollo.fullstack.model.RoleModel;
import com.evollo.fullstack.model.RoleName;
import com.evollo.fullstack.model.UserModel;
import com.evollo.fullstack.payload.SignUpRequest;
import com.evollo.fullstack.repository.RoleRepository;
import com.evollo.fullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Transactional
    public void createNewUser(SignUpRequest signUpRequest) throws UserAlreadyTakenException, RoleNotSetException {
        verifyUserExists(signUpRequest);

        // Creating user's account
        UserModel user = new UserModel(signUpRequest.getName(), generateUsernameDefault()
                , generatePasswordDefault(signUpRequest.getName()));

        RoleModel userRole = roleRepository.findByRole(signUpRequest.getRole())
                .orElseThrow(() -> new RoleNotSetException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
    }

    private void verifyUserExists(SignUpRequest signUpRequest) throws UserAlreadyTakenException {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyTakenException("Username is already taken!");
        }
    }

    private String generateUsernameDefault() {
        String hash = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        log.info("username-" + hash);
        return "username-" + hash;
    }

    private String generatePasswordDefault(String name) {
        String hash = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        log.info(hash);
        return passwordEncoder.encode(hash);
    }

}
