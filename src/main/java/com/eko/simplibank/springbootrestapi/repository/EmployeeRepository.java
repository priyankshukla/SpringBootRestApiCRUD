package com.eko.simplibank.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eko.simplibank.springbootrestapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
