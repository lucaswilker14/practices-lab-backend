package com.evollo.fullstack.repository;

import com.evollo.fullstack.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

    Boolean existsByUsername(String username);
}
