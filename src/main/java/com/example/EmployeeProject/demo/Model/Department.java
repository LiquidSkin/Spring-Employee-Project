package com.example.EmployeeProject.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "Department")


public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String departmentName;

    private String departmentDetails;


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    Set<Employee> employees;

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }


    public Department(Long id, @NotNull String departmentName, @NotNull String departmentDetails, Set<Employee> employees) {
        this.id = id;
        this.departmentName = departmentName;
        this.departmentDetails = departmentDetails;
        this.employees = employees;
    }

    public Department() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDetails() {
        return departmentDetails;
    }

    public void setDepartmentDetails(String departmentDetails) {
        this.departmentDetails = departmentDetails;
    }
}
