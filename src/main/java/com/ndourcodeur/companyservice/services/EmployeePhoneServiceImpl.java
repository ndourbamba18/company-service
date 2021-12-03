package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.dto.EmployeePhoneDto;
import com.ndourcodeur.companyservice.entities.Employee;
import com.ndourcodeur.companyservice.entities.EmployeePhone;
import com.ndourcodeur.companyservice.exception.ResourceNotFoundException;
import com.ndourcodeur.companyservice.repository.EmployeePhoneRepository;
import com.ndourcodeur.companyservice.repository.EmployeeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class EmployeePhoneServiceImpl implements EmployeePhoneService {

    private final EmployeePhoneRepository employeePhoneRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeePhoneServiceImpl(EmployeePhoneRepository employeePhoneRepository,
                                    EmployeeRepository employeeRepository) {

        this.employeePhoneRepository = employeePhoneRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeePhone addEmployeePhone(EmployeePhoneDto dto, Long idEmployee) {
        EmployeePhone employeePhone = new EmployeePhone();
        employeePhone.setId(dto.getId());
        employeePhone.setPhone(dto.getPhone());
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not found with id:"+idEmployee));
        employee.addEmployeePhoneToEmployee(employeePhone);
        return employeePhoneRepository.save(employeePhone);
    }

    @Override
    public EmployeePhone editEmployeePhone(EmployeePhoneDto dto, Long id) {
        EmployeePhone employeePhone = employeePhoneRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee Phone not found with id:"+id));
        employeePhone.setId(dto.getId());
        employeePhone.setPhone(dto.getPhone());
        return employeePhoneRepository.save(employeePhone);
    }

    @Override
    public void deleteEmployeePhone(Long id) {
        EmployeePhone existsEmployeePhone = employeePhoneRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee Phone not found with id: "+id));
        employeePhoneRepository.delete(existsEmployeePhone);
    }

    @Override
    public EmployeePhone findEmployeePhone(Long id) {
        EmployeePhone existsEmployeePhone = employeePhoneRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee Phone not found with id: "+id));
        return existsEmployeePhone;
    }

    @Override
    public List<EmployeePhone> findEmployeePhones(Long idEmployee) {
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not found with id:"+idEmployee));
        List<EmployeePhone> employeePhones = employee.getEmployeePhones();
        return employeePhones;
    }
}
