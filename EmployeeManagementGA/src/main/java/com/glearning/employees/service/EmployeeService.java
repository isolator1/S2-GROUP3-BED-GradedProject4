package com.glearning.employees.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.glearning.employees.model.Employee;
import com.glearning.employees.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeerepository;
	
	public Employee saveEmployee(Employee employee) {
		return this.employeerepository.save(employee);
	}
	
	public Set<Employee> fetchAllEmployees() {
		return new HashSet<>(this.employeerepository.findAll());
	}
	
	public Employee fetchEmployeeById(long empId) {
		return this.employeerepository.findById(empId).orElseThrow();
	}
	
	public void deleteEmployeeById(long empId) {
		this.employeerepository.deleteById(empId);
	}
	
//	public List<Employee> findEmployeeWithSorting(String field) {
//		return this.employeerepository.findAll(Sort.by(Sort.Direction.ASC, field));
//	}
	
}