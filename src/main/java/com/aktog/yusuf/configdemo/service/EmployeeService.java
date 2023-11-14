package com.aktog.yusuf.configdemo.service;


import com.aktog.yusuf.configdemo.entity.Employee;
import com.aktog.yusuf.configdemo.entity.EmployeeDto;
import com.aktog.yusuf.configdemo.entity.request.employee.CreateEmployeeRequest;
import com.aktog.yusuf.configdemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save (CreateEmployeeRequest request){
        Employee employee = new Employee(request.name(),request.surname(),request.mail());

        return employeeRepository.save(employee);
    }

    @SuppressWarnings("java:S6204")
    public List<EmployeeDto> getEmployeeDtos(){
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
public EmployeeDto convertToDto(Employee employee){
        return new EmployeeDto(employee.getName(),employee.getSurname(),employee.getMail());
}
    public Employee getById(String id){
        return employeeRepository.findById(id).orElse(null);
    }
}
