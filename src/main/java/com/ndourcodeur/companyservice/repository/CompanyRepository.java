package com.ndourcodeur.companyservice.repository;

import com.ndourcodeur.companyservice.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByName(String name);

    boolean existsByName(String name);

}
