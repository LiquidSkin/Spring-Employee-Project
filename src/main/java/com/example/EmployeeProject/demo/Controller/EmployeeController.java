package com.example.EmployeeProject.demo.Controller;

import com.example.EmployeeProject.demo.Model.Employee;
import com.example.EmployeeProject.demo.Repository.EmployeeRepository;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController

public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    List<Employee> getAllEmployees()
    {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
    @PostMapping("/employees")
    Employee addEmployee(@Valid @RequestBody Employee employee)
    {
      return employeeRepository.save(employee);
    }
    @PutMapping("/employees/{id}")
    Employee updateProduct(@PathVariable(name = "id") Long id, @Valid @RequestBody Employee employee) throws Exception
    {
        Optional<Employee> oldEmployee = employeeRepository.findById(id);
        if(oldEmployee.isPresent())
        {
            oldEmployee.get().setEmail(employee.getEmail());
            oldEmployee.get().setFirstName(employee.getFirstName());
            oldEmployee.get().setLastName(employee.getLastName());
            oldEmployee.get().setSalary(employee.getSalary());
            return employeeRepository.save(oldEmployee.get());
        }
        else
        {
            throw new Exception("Invalid ID");
        }
    }
    @DeleteMapping("/employees/{id}")
    String deleteEmployee(@PathVariable Long id) throws Exception
    {
     Optional<Employee> employee = employeeRepository.findById(id);
     if(employee.isPresent())
     {
       employeeRepository.deleteById(id);
       return "deleted";
     }
     else
     {
       throw new Exception("Invalid ID");
     }
    }






}
