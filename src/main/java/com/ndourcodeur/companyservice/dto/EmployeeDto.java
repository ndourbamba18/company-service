package com.ndourcodeur.companyservice.dto;

import com.ndourcodeur.companyservice.entities.Department;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDto {

    private Long id;
    @NotBlank(message = "Employee First Name is required!")
    private String firstName;
    @NotBlank(message = "Employee Last Name is required!")
    private String lastName;
    @NotBlank(message = "Employee Email is required!")
    @Email(message = "Please, enter a valid email.")
    @Size(max = 150, message = "Employee Email must be 150 characters.")
    private String email;
    @Lob
    private String address;
    @Min(value = 0, message = "Employee Salary must be positive.")
    private double salary;
    private String hiringDate;
    private String birthDate;
    @NotBlank(message = "Employee Job's is required!")
    private String jobTitle;
    private Department department;
    private List<String> employeePhones = new ArrayList<>();
    private List<String> projects = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<String> getEmployeePhones() {
        return employeePhones;
    }

    public void setEmployeePhones(List<String> employeePhones) {
        this.employeePhones = employeePhones;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }
}
