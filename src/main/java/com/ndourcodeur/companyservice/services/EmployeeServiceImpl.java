package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.dto.EmployeeDto;
import com.ndourcodeur.companyservice.entities.Department;
import com.ndourcodeur.companyservice.entities.Employee;
import com.ndourcodeur.companyservice.exception.ResourceNotFoundException;
import com.ndourcodeur.companyservice.repository.DepartmentRepository;
import com.ndourcodeur.companyservice.repository.EmployeeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee addEmployee(EmployeeDto dto, Long idDepartment) {
        Department department = departmentRepository.findById(idDepartment)
                .orElseThrow( () -> new ResourceNotFoundException("Department not found with id: "+idDepartment));
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setAddress(dto.getAddress());
        employee.setSalary(dto.getSalary());
        employee.setHiringDate(dto.getHiringDate());
        employee.setBirthDate(dto.getBirthDate());
        employee.setJobTitle(dto.getJobTitle());
        department.addEmployeeToDepartment(employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee editEmployee(EmployeeDto dto, Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not found with id: "+id));
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setAddress(dto.getAddress());
        employee.setSalary(dto.getSalary());
        employee.setHiringDate(dto.getHiringDate());
        employee.setBirthDate(dto.getBirthDate());
        employee.setJobTitle(dto.getJobTitle());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not found with id: "+id));
        employeeRepository.delete(employee);
    }

    @Override
    public Employee findEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not found with id: "+id));
        return employee;
    }

    @Override
    public List<Employee> findEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
}
