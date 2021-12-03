package com.ndourcodeur.companyservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "departments", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class Department implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Department Code is required!")
    private String code;
    @NotBlank(message = "Department Location is required!")
    private String location;
    @NotBlank(message = "Department Description is required!")
    @Lob
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    @JsonBackReference(value = "company")
    private Company company;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Employee> employees;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Project> projects;

    public void addEmployeeToDepartment(Employee employee){
        if (getEmployees()==null){
            this.employees = new ArrayList<>();
        }
        getEmployees().add(employee);
        employee.setDepartment(this);
    }

    public void addProjectToDepartment(Project project){
        if (getProjects()==null){
            this.projects = new ArrayList<>();
        }
        getProjects().add(project);
        project.setDepartment(this);
    }

    public Department() { }

    public Department(String code, String location, String description, Date createdAt,
                      Company company, List<Employee> employees, List<Project> projects) {
        this.code = code;
        this.location = location;
        this.description = description;
        this.createdAt = createdAt;
        this.company = company;
        this.employees = employees;
        this.projects = projects;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", employees=" + employees +
                ", projects=" + projects +
                '}';
    }
}
