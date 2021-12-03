package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.dto.EmployeePhoneDto;
import com.ndourcodeur.companyservice.entities.EmployeePhone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeePhoneService {

    EmployeePhone addEmployeePhone(EmployeePhoneDto dto, Long idEmployee);

    EmployeePhone editEmployeePhone(EmployeePhoneDto dto, Long id);

    void deleteEmployeePhone(Long id);

    EmployeePhone findEmployeePhone(Long id);

    List<EmployeePhone> findEmployeePhones(Long id);
}
