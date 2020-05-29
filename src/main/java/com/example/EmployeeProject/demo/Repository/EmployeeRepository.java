package com.example.EmployeeProject.demo.Repository;

import com.example.EmployeeProject.demo.Model.Department;
import com.example.EmployeeProject.demo.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(Department department);
}
