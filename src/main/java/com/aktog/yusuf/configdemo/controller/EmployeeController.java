package com.aktog.yusuf.configdemo.controller;


import com.aktog.yusuf.configdemo.entity.Employee;
import com.aktog.yusuf.configdemo.entity.EmployeeDto;
import com.aktog.yusuf.configdemo.entity.request.employee.CreateEmployeeRequest;
import com.aktog.yusuf.configdemo.service.EmployeeService;
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
