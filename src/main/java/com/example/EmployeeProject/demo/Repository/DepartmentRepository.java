package com.example.EmployeeProject.demo.Repository;

import com.example.EmployeeProject.demo.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

