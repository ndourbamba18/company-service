package com.ndourcodeur.companyservice.repository;

import com.ndourcodeur.companyservice.entities.EmployeePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePhoneRepository extends JpaRepository<EmployeePhone, Long> {

}
