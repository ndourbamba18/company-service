package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.dto.EmployeeDto;
import com.ndourcodeur.companyservice.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee addEmployee(EmployeeDto dto, Long idDepartment);

    Employee editEmployee(EmployeeDto dto, Long id);

    void deleteEmployee(Long id);

    Employee findEmployee(Long id);

    List<Employee> findEmployees();
}
