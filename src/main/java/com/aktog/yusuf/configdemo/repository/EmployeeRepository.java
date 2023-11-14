package com.aktog.yusuf.configdemo.repository;

import com.aktog.yusuf.configdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
