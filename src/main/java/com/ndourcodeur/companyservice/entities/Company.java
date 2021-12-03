package com.ndourcodeur.companyservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "companies",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
public class Company implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @NotBlank(message = "Company Name is required!")
    @Size(min = 2, max = 100, message = "Company Name must be between 2 and 100 characters.")
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @NotBlank(message = "Company Location is required!")
    private String location;
    @NotBlank(message = "Company Description is required!")
    @Lob
    private String description;
    @ManyToOne
    @JsonBackReference(value = "user")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Department> departments;

    public Company() { }

    public Company(String name, Date createdAt, String location, String description,
                   User user, List<Department> departments) {
        this.name = name;
        this.createdAt = createdAt;
        this.location = location;
        this.description = description;
        this.user = user;
        this.departments = departments;
    }

    public void addDepartmentToCompany(Department department){
        if (getDepartments()==null){
            this.departments = new ArrayList<>();
        }
        getDepartments().add(department);
        department.setCompany(this);
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", departments=" + departments +
                '}';
    }
}
