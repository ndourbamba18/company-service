package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.dto.DepartmentDto;
import com.ndourcodeur.companyservice.entities.Company;
import com.ndourcodeur.companyservice.entities.Department;
import com.ndourcodeur.companyservice.exception.ResourceNotFoundException;
import com.ndourcodeur.companyservice.repository.CompanyRepository;
import com.ndourcodeur.companyservice.repository.DepartmentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Department addDepartment(DepartmentDto dto, Long idCompany) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setCode(dto.getCode());
        department.setCreatedAt(new Date());
        department.setDescription(dto.getDescription());
        department.setLocation(dto.getLocation());
        Company company = companyRepository.findById(idCompany)
                .orElseThrow( () -> new ResourceNotFoundException("Company not found with id: "+idCompany));
        company.addDepartmentToCompany(department);
        return departmentRepository.save(department);
    }

    @Override
    public Department editDepartment(DepartmentDto dto, Long idDepartment) {
        Department existsDepartment= departmentRepository.findById(idDepartment)
                .orElseThrow( () -> new ResourceNotFoundException("Department not found with id: "+idDepartment));
        existsDepartment.setId(dto.getId());
        existsDepartment.setCode(dto.getCode());
        existsDepartment.setDescription(dto.getDescription());
        existsDepartment.setLocation(dto.getLocation());
        return departmentRepository.save(existsDepartment);
    }

    @Override
    public void deleteDepartment(Long idDepartment) {
        Department existsDepartment= departmentRepository.findById(idDepartment)
                .orElseThrow( () -> new ResourceNotFoundException("Department not found with id: "+idDepartment));
        departmentRepository.delete(existsDepartment);
    }

    @Override
    public Department findDepartment(Long idDepartment) {
        Department department= departmentRepository.findById(idDepartment)
                .orElseThrow( () -> new ResourceNotFoundException("Department not found with id: "+idDepartment));
        return department;
    }

    @Override
    public List<Department> findDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }
}
