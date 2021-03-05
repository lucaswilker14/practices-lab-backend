package com.evollo.fullstack.runner;

import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.model.RoleModel;
import com.evollo.fullstack.model.RoleName;
import com.evollo.fullstack.model.UserModel;
import com.evollo.fullstack.repository.EmployeeRepository;
import com.evollo.fullstack.repository.RoleRepository;
import com.evollo.fullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        // CREATE ROLES IN DB MEMORY
        RoleModel roleAdmin = new RoleModel(RoleName.ROLE_ADMIN);
        RoleModel roleUser = new RoleModel(RoleName.ROLE_USER);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        // CREATE EMPLOYEE ONE
        EmployeeModel employee = new EmployeeModel("admin default", "admin@gmail.com", "000.000.000-00",
                "System Admin Default", 50F,"ADMIN");
        employeeRepository.save(employee);

        // CREATE ADMIN DEFAULT
        UserModel admin = new UserModel("admin default", "admin", "123");
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        RoleModel adminRole = roleRepository.findByRole(RoleName.ROLE_ADMIN).orElseThrow(() -> new Exception("User Role not set."));
        RoleModel userRole = roleRepository.findByRole(RoleName.ROLE_USER).orElseThrow(() -> new Exception("User Role not set."));
        admin.getRoles().add(adminRole);
        admin.getRoles().add(userRole);
        UserModel result = userRepository.save(admin);

        log.warn(userRepository.findAll());
        log.warn(employeeRepository.findAll());

    }
}
