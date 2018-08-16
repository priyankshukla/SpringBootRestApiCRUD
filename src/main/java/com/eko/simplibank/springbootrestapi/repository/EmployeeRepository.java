package com.eko.simplibank.springbootrestapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eko.simplibank.springbootrestapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	List<Employee> findByName(String empName);

}
