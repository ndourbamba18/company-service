package com.ndourcodeur.companyservice.dto;

import com.ndourcodeur.companyservice.entities.Employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeePhoneDto {

    private Long id;
    @NotBlank(message = "Phone Number is required!")
    @Size(max = 20, message = "Employee Phone Number must be 20 characters.")
    private String phone;
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
