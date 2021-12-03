package com.ndourcodeur.companyservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "projects", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Project implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    @JsonBackReference(value = "department")
    private Department department;
    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
    @ManyToOne
    @JsonBackReference(value = "employee")
    private Employee employee;

    public Project() { }

    public Project(String name, String location, String description, Process process, Date createdAt,
                   Department department, Employee employee) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.process = process;
        this.createdAt = createdAt;
        this.department = department;
        this.employee = employee;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", process='" + process + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
