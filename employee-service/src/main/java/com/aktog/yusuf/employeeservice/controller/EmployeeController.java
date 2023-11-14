package com.aktog.yusuf.employeeservice.controller;

import com.aktog.yusuf.employeeservice.entity.Employee;
import com.aktog.yusuf.employeeservice.entity.EmployeeDto;
import com.aktog.yusuf.employeeservice.entity.request.CreateEmployeeRequest;
import com.aktog.yusuf.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody CreateEmployeeRequest request) {
        return ResponseEntity.ok(employeeService.save(request));
    }

    @GetMapping("/list")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/list/dto")
    public List<EmployeeDto> getEmployeeDtos() {
        return employeeService.getEmployeeDtos();
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable String id) {
        Employee employee = employeeService.getById(id);
        return employee;
    }
}
