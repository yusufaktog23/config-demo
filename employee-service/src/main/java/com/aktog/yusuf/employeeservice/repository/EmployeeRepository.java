package com.aktog.yusuf.employeeservice.repository;


import com.aktog.yusuf.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
