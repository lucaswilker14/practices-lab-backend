package com.plabs.backend.repository;

import com.plabs.backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

    Boolean existsByUsername(String username);

    UserModel findByName(String name);

    UserModel findByid(Long id);
}
