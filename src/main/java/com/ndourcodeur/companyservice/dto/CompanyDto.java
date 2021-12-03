package com.ndourcodeur.companyservice.dto;

import com.ndourcodeur.companyservice.entities.User;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CompanyDto {

    private Long id;
    @NotBlank(message = "Company Name is required!")
    @Size(min = 2, max = 100, message = "Company Name must be between 2 and 100 characters.")
    private String name;
    @NotBlank(message = "Company Location is required!")
    private String location;
    @NotBlank(message = "Company Description is required!")
    @Lob
    private String description;
    private User user;
    private List<String> departments = new ArrayList<>();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }
}
