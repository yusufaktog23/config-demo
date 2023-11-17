package com.aktog.yusuf.employeeservice.service;


import com.aktog.yusuf.employeeservice.entity.Employee;
import com.aktog.yusuf.employeeservice.entity.EmployeeDto;
import com.aktog.yusuf.employeeservice.entity.request.CreateEmployeeRequest;
import com.aktog.yusuf.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private static final Logger log= LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(CreateEmployeeRequest request) {
        Employee employee = new Employee(request.name(), request.surname(), request.mail());

        Employee savedEmployee= employeeRepository.save(employee);
        log.info("EmployeeService.save() is called with request: {} and saved employee: {}"
                , request, savedEmployee);
        return savedEmployee;
    }

    @SuppressWarnings("java:S6204")
    public List<EmployeeDto> getEmployeeDtos() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDto> employeeDtos=
                employees.stream().map(this::convertToDto).toList();
        log.info("EmployeeService.getEmployeeDtos() is called and found {} employees", employeeDtos.size());
        return employeeDtos;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees= employeeRepository.findAll();
        log.info("EmployeeService.getEmployees() is called and found {} employees", employees.size());
        return employees;
    }

    public EmployeeDto convertToDto(Employee employee) {
        log.info("EmployeeService.convertToDto() is called with employee: {}", employee);
        return new EmployeeDto(employee.getName(), employee.getSurname(), employee.getMail());
    }

    public Employee getById(String id) {
        log.info("EmployeeService.getById() is called with id: {}", id);
        Employee employee = employeeRepository.findById(id).orElse(null);
        log.info("Employee is: {}", employee);
        return employee;
    }
}
