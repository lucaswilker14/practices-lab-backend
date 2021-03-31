package com.plabs.backend.repository;

import com.plabs.backend.model.RoleModel;
import com.plabs.backend.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    Optional<RoleModel> findByRole(RoleName name);
}
