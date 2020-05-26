package com.example.EmployeeProject.demo.Repository;

import com.example.EmployeeProject.demo.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
