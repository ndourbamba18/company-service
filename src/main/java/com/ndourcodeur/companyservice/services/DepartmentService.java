package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.dto.DepartmentDto;
import com.ndourcodeur.companyservice.entities.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    Department addDepartment(DepartmentDto dto, Long idCompany);

    Department editDepartment(DepartmentDto dto, Long idDepartment);

    void deleteDepartment(Long idDepartment);

    Department findDepartment(Long idDepartment);

    List<Department> findDepartments();

}
