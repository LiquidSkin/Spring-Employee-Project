package com.example.EmployeeProject.demo.Controller;

import com.example.EmployeeProject.demo.Model.Department;
import com.example.EmployeeProject.demo.Model.Employee;
import com.example.EmployeeProject.demo.Repository.DepartmentRepository;
import com.example.EmployeeProject.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController

public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/employees")
    List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @GetMapping("/departments/{departmentId}/employees")
    List<Employee> getEmployeeByDepartment(@PathVariable(name = "departmentId") Long departmentId) throws Exception {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            return employeeRepository.findByDepartment(department.get());
        } else {
            throw new Exception("Invalid ID");
        }
    }

    @PostMapping("/departments/{departmentId}/employees")
    Employee addDepartment(@PathVariable(name = "departmentId") Long departmentId, @Valid @RequestBody Employee employee) throws Exception {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            employee.setDepartment(department.get());
            return employeeRepository.save(employee);
        } else {
            throw new Exception("Invalid ID");
        }
    }


    @PutMapping("/departments/{departmentId}/employees/{employeeId}")
    Employee updateEmployee(@PathVariable(name = "departmentId") Long departmentId, @PathVariable(name = "employeeId") Long employeeId,@Valid @RequestBody Employee employee) throws Exception {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            Optional<Employee> oldEmployee = employeeRepository.findById(employeeId);
            if (oldEmployee.isPresent()) {
                oldEmployee.get().setFirstName(employee.getFirstName());
                oldEmployee.get().setLastName(employee.getLastName());
                oldEmployee.get().setSalary(employee.getSalary());
                oldEmployee.get().setEmail(employee.getEmail());
                return employeeRepository.save(oldEmployee.get());
            } else {
                throw new Exception("Invalid Employee ID");
            }
        } else {
            throw new Exception("Invalid Department ID");
        }
    }
    @PatchMapping("/departments/{departmentId}/employees/{employeeId}")
    Employee updateEmployeeSalary(@PathVariable(name = "departmentId") Long departmentId, @PathVariable(name = "employeeId") Long employeeId,@Valid @RequestBody Employee employee) throws Exception
    {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            Optional<Employee> employeeOld = employeeRepository.findById(employeeId);
            if(employeeOld.isPresent())
            {
                employeeOld.get().setSalary(employee.getSalary());
                return employeeRepository.save(employeeOld.get());
            }
            else
            {
                throw new Exception("Invalid ID");
            }

        }
        else
        {
            throw new Exception("Invalid Department ID");
        }

    }

    @DeleteMapping("/departments/{departmentId}/employees/{employeeId}")
    String deleteEmployee(@PathVariable(name = "employeeId") Long employeeId, @PathVariable(name = "departmentId") Long departmentId) throws Exception {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (employee.isPresent()) {
                employeeRepository.deleteById(employeeId);
                return "deleted";
            } else {
                return "Cant Delete, Invalid Employee ID";
            }
        } else {
            return "Cannot Delete, Invalid Department ID";
        }

    }
}







