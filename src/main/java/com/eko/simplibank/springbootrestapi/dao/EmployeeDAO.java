package com.eko.simplibank.springbootrestapi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eko.simplibank.springbootrestapi.model.Employee;
import com.eko.simplibank.springbootrestapi.repository.EmployeeRepository;

@Service
public class EmployeeDAO {

	@Autowired
	EmployeeRepository employeeRepository;
	
	/*save employee*/
	public Employee save(Employee emp) {
		System.out.println("EmployeeDAO.save()");
		return employeeRepository.save(emp);
	}
	
	/*search all employees*/
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	
	/*get an employee*/
	public Employee get(Long employeeId) {
		return employeeRepository.findOne(employeeId);
	}
	
	
	/*delete an employee*/
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}
}
