package com.evollo.fullstack.service;

import com.evollo.fullstack.exception.FieldInvalidException;
import com.evollo.fullstack.exception.RoleNotSetException;
import com.evollo.fullstack.exception.UserAlreadyTakenException;
import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.model.RoleModel;
import com.evollo.fullstack.model.RoleName;
import com.evollo.fullstack.model.UserModel;
import com.evollo.fullstack.payload.SignUpRequest;
import com.evollo.fullstack.repository.RoleRepository;
import com.evollo.fullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    private final EmailSendService emailSendService;


    @Transactional
    public void registeNewUser(EmployeeModel employeeModel)
            throws UserAlreadyTakenException, RoleNotSetException {

        SignUpRequest newUserRegistered = createNewSignUp(employeeModel);
        verifyUserExistsInDb(newUserRegistered);

        String generatePassword = generatePasswordDefault();

        UserModel user = new UserModel(newUserRegistered.getName(), generateUsernameDefault()
                , passwordEncoder.encode(generatePassword));

        RoleModel userRole = roleRepository.findByRole(newUserRegistered.getRole())
                .orElseThrow(() -> new RoleNotSetException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);

        //send email
        sendEmailToUser(employeeModel.getEmail(), user.getUsername(), generatePassword);

    }

    public UserModel getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public UserModel update(Long id, UserModel newUserModel) throws FieldInvalidException {

        UserModel oldUserModel = userRepository.findByid(id);
        boolean passwordDiff = passwordEncoder.matches(oldUserModel.getPassword(), newUserModel.getPassword());

        if (!newUserModel.getName().isBlank()) {
            oldUserModel.setName(newUserModel.getName());
        }else {
            throw new FieldInvalidException("Invalid Field");
        }

        if (!newUserModel.getUsername().isBlank()) {
            oldUserModel.setUsername(newUserModel.getUsername());
        }else {
            throw new FieldInvalidException("Invalid Field");
        }

        if (!newUserModel.getPassword().isBlank() && !passwordDiff) {
            String newPassword = passwordEncoder.encode(newUserModel.getPassword());
            oldUserModel.setPassword(newPassword);
        }

        return userRepository.save(oldUserModel);
    }

    private void sendEmailToUser(String toEmail, String username, String password) {
        String body = String.format("Login credentials:\n \n * username: %s \n * password: %s",
                username, password);
        String subject = "New credentials created";
        emailSendService.sendEmail(toEmail, body, subject);
    }

    private void verifyUserExistsInDb(SignUpRequest signUpRequest) throws UserAlreadyTakenException {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyTakenException("Username is already taken!");
        }
    }

    private String generateUsernameDefault() {
        String hash = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        log.info("username-" + hash);
        return "username-" + hash;
    }

    private String generatePasswordDefault() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    private SignUpRequest createNewSignUp(EmployeeModel employeeModel) {
        RoleName roleName = (RoleName.ROLE_USER.name().contains(employeeModel.getPermission()))
                ? RoleName.ROLE_USER : RoleName.ROLE_ADMIN;
        return new SignUpRequest(employeeModel.getName(), "", "", roleName);
    }

    public void removeUser(Long id) {
        this.userRepository.deleteById(id);
    }

}
