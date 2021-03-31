package com.plabs.backend.repository;

import com.plabs.backend.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<EmployeeModel, Long> {

    EmployeeModel findByid(Long id);

    Boolean existsByCpf(String cpf);

    EmployeeModel findByCpf(String cpf);

}
