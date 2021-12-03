package com.ndourcodeur.companyservice.repository;

import com.ndourcodeur.companyservice.entities.Role;
import com.ndourcodeur.companyservice.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
