package com.ndourcodeur.companyservice.dto;

import com.ndourcodeur.companyservice.entities.Company;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDto {

    private Long id;
    @NotBlank(message = "Department Code is required!")
    private String code;
    @NotBlank(message = "Department Location is required!")
    private String location;
    @NotBlank(message = "Department Description is required!")
    private String description;
    private Company company;
    private List<String> employees = new ArrayList<>();
    private List<String> projects = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }
}
