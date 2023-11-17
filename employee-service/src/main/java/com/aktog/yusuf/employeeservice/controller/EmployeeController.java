package com.aktog.yusuf.employeeservice.controller;

import com.aktog.yusuf.employeeservice.entity.Employee;
import com.aktog.yusuf.employeeservice.entity.EmployeeDto;
import com.aktog.yusuf.employeeservice.entity.request.CreateEmployeeRequest;
import com.aktog.yusuf.employeeservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    private final EmployeeService employeeService;
    private static final Logger log= LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping
    public String welcome() {
        return "WELCOME U LITTLE B!";
    }
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody CreateEmployeeRequest request) {
        log.info("EmployeeController.save() is called");
        return ResponseEntity.ok(employeeService.save(request));
    }

    @GetMapping("/list")
    public List<Employee> getEmployees() {
        log.info("EmployeeController.getEmployees() is called");
        return employeeService.getEmployees();
    }

    @GetMapping("/list/dto")
    public List<EmployeeDto> getEmployeeDtos() {
        log.info("EmployeeController.getEmployeeDtos() is called");
        return employeeService.getEmployeeDtos();
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable String id) {
        log.info("EmployeeController.getById() is called");
        return employeeService.getById(id);
    }
}
