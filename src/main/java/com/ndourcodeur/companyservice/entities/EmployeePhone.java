package com.ndourcodeur.companyservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "employeePhones", uniqueConstraints = {@UniqueConstraint(columnNames = "phone")})
public class EmployeePhone implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @NotBlank(message = "Phone Number is required!")
    @Size(max = 20, message = "Employee Phone Number must be 20 characters.")
    private String phone;
    @ManyToOne
    @JsonBackReference(value = "employee")
    private Employee employee;

    public EmployeePhone() { }

    public EmployeePhone(String phone, Employee employee) {
        this.phone = phone;
        this.employee = employee;
    }

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

    @Override
    public String toString() {
        return "EmployeePhone{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                '}';
    }
}
