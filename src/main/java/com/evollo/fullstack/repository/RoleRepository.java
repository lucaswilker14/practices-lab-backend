package com.evollo.fullstack.repository;

import com.evollo.fullstack.model.RoleModel;
import com.evollo.fullstack.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    Optional<RoleModel> findByRole(RoleName name);
}
