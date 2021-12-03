package com.ndourcodeur.companyservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Employee implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @NotBlank(message = "Employee First Name is required!")
    private String firstName;
    @NotBlank(message = "Employee Last Name is required!")
    private String lastName;
    @NotBlank(message = "Employee Email is required!")
    @NaturalId
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
    @ManyToOne
    @JsonBackReference(value = "department")
    private Department department;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<EmployeePhone> employeePhones;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_projects",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<Project> projects;

    public void addEmployeePhoneToEmployee(EmployeePhone employeePhone){
        if (getEmployeePhones()==null){
            this.employeePhones = new ArrayList<>();
        }
        getEmployeePhones().add(employeePhone);
        employeePhone.setEmployee(this);
    }

    public void addProjectToEmployee(Project project){
        if (getProjects()==null){
            this.projects = new ArrayList<>();
        }
        this.projects.add(project);
        project.setEmployee(this);
    }

    public Employee() { }

    public Employee(String firstName, String lastName, String email, String address, double salary,
                    String hiringDate, String birthDate, String jobTitle, Department department,
                    List<EmployeePhone> employeePhones, List<Project> projects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.hiringDate = hiringDate;
        this.birthDate = birthDate;
        this.jobTitle = jobTitle;
        this.department = department;
        this.employeePhones = employeePhones;
        this.projects = projects;
    }

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

    public List<EmployeePhone> getEmployeePhones() {
        return employeePhones;
    }

    public void setEmployeePhones(List<EmployeePhone> employeePhones) {
        this.employeePhones = employeePhones;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", hiringDate='" + hiringDate + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
