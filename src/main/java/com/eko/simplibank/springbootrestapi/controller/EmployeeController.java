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

import com.eko.simplibank.springbootrestapi.model.Employee;
import com.eko.simplibank.springbootrestapi.repository.EmployeeRepository;

@RestController
@RequestMapping("/company")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeRepository.save(emp);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	//Get Employee
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empId) {
		Employee employee = employeeRepository.findOne(empId);
		if (employee ==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(employee);
	}
		
	// Find by name
	@GetMapping("/employee-name/{name}")
	public ResponseEntity<Employee> findByName(@PathVariable (value="name") String empName) {
		List<Employee> names = employeeRepository.findByName(empName);
		System.out.println(names);
	 return ResponseEntity.ok().body(names.get(0));
	}
	
	//Update Employee
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long employeeId, @Valid @RequestBody Employee empDetails) {
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null)
			return ResponseEntity.notFound().build();
		
		employee.setName(empDetails.getName());
		employee.setDesignation(empDetails.getDesignation());
		employee.setCreateDate(empDetails.getCreateDate());
		employee.setExperties(empDetails.getExperties());
		
		Employee updateEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok().body(updateEmployee);
		
	
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long id) {
		Employee employee = employeeRepository.findOne(id);
		if(employee == null)
			return ResponseEntity.notFound().build();
	
		employeeRepository.delete(employee);
		
		return ResponseEntity.ok().build();
	}
}
