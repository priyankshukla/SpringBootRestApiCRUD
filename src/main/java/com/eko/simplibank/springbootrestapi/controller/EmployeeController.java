package com.eko.simplibank.springbootrestapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eko.simplibank.springbootrestapi.dao.EmployeeDAO;
import com.eko.simplibank.springbootrestapi.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		System.out.println("EmployeeController.createEmployee()");
		return employeeDAO.save(emp);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeDAO.findAll();
	}
	
	//Get Employee
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empId) {
		Employee employee = employeeDAO.get(empId);
		if (employee ==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(employee);
	}
	
	
	//Update Employee
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long employeeId, @Valid @RequestBody Employee empDetails) {
		Employee employee = employeeDAO.get(employeeId);
		if (employee == null)
			return ResponseEntity.notFound().build();
		
		employee.setName(empDetails.getName());
		employee.setDesignation(empDetails.getDesignation());
		employee.setCreateDate(empDetails.getCreateDate());
		employee.setExperties(empDetails.getExperties());
		
		Employee updateEmployee = employeeDAO.save(employee);
		return ResponseEntity.ok().body(updateEmployee);
		
	
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long id) {
		Employee employee = employeeDAO.get(id);
		if(employee == null)
			return ResponseEntity.notFound().build();
	
		employeeDAO.delete(employee);
		
		return ResponseEntity.ok().build();
	}
}
