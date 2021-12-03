package com.ndourcodeur.companyservice.dto;

import com.ndourcodeur.companyservice.entities.Department;
import com.ndourcodeur.companyservice.entities.Employee;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProjectDto {

    private Long id;
    @NotBlank(message = "Project Name is required!")
    @Size(max = 150, message = "Project Name must be 150 characters.")
    private String name;
    @NotBlank(message = "Project Location is required!")
    @Size(max = 100, message = "Project Name must be 100 characters.")
    private String location;
    @Lob
    private String description;
    @Enumerated(EnumType.STRING)
    private Process process;
    private Department department;
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
