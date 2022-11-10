package com.glearning.employees.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glearning.employees.model.Employee;
import com.glearning.employees.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeRestController {
	
	private final EmployeeService employeeService;
	
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return this.employeeService.saveEmployee(employee);
	}
	
	@GetMapping
	public Set<Employee> fetchAllEmployees() {
		return this.employeeService.fetchAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long empId) {
		return this.employeeService.fetchEmployeeById(empId);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable("id") long empId) {
		this.employeeService.deleteEmployeeById(empId);
	}
	
	
//	@GetMapping("/{firstName}")
//	public List<Employee> getEmployeewithSort(@PathVariable String field) {
//		List<Employee> allEmployees = employeeService.findEmployeeWithSorting(field);
//		return allEmployees;
//	}
}
