package com.example.EmployeeProject.demo.Controller;

import com.example.EmployeeProject.demo.Model.Department;
import com.example.EmployeeProject.demo.Model.Employee;
import com.example.EmployeeProject.demo.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    List<Department> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @PostMapping("/departments")
    Department addDepartment(@Valid @RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/departments/{departmentId}")
    Department updateDepartment(@PathVariable(name = "departmentId") Long departmentId, @Valid @RequestBody Department department) throws Exception {
        Optional<Department> oldDepartment = departmentRepository.findById(departmentId);
        if (oldDepartment.isPresent()) {
            oldDepartment.get().setDepartmentName(department.getDepartmentName());
            oldDepartment.get().setDepartmentDetails(department.getDepartmentDetails());
            return departmentRepository.save(oldDepartment.get());
        } else {
            throw new Exception("Invalid Department ID");
        }

    }
    @PatchMapping("/departments/{departmentId}")
    Department updateDepartmentName(@PathVariable(name = "departmentId") Long departmentId,  @Valid @RequestBody Department department) throws Exception
    {
        Optional<Department> oldDepartment = departmentRepository.findById(departmentId);
        if(oldDepartment.isPresent())
        {
            oldDepartment.get().setDepartmentName(department.getDepartmentName());
            return departmentRepository.save(oldDepartment.get());
        }
        else
        {
            throw new Exception("Invalid Department ID");
        }

    }


    @DeleteMapping("/departments")
    String deleteDepartment(@PathVariable Long id) throws Exception {
        Optional<Department> oldDepartment = departmentRepository.findById(id);
        if (oldDepartment.isPresent()) {
            departmentRepository.deleteById(id);
            return "deleted";
        } else {
            throw new Exception("Invalid ID");
        }
    }


}
