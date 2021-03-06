package com.evollo.fullstack.repository;


import com.evollo.fullstack.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {

    boolean existsByCnpj(String cnpj);

    CompanyModel findByCnpj(String cnpj);

    CompanyModel findByid(Long id);

}
