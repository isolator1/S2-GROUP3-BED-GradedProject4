package com.glearning.employees.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.glearning.employees.model.Employee;
import com.glearning.employees.model.Role;
import com.glearning.employees.model.User;
import com.glearning.employees.repository.EmployeeRepository;
import com.glearning.employees.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private final EmployeeRepository employeeRepository;
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent event) {
		Employee ramesh = new Employee();
		ramesh.setFirstName("Ramesh");
		ramesh.setLastName("Narayanan");
		ramesh.setEmail("ramesh@gmail.com");
		this.employeeRepository.save(ramesh);
		
		Employee suresh = new Employee();
		suresh.setFirstName("Suresh");
		suresh.setLastName("Balasubramanian");
		suresh.setEmail("suresh@gmail.com");

		this.employeeRepository.save(suresh);
		
		Employee ganesh = new Employee();
		suresh.setFirstName("Ganesh");
		suresh.setLastName("Venkat");
		suresh.setEmail("ganesh@gmail.com");

		this.employeeRepository.save(ganesh);
		
		Employee Akash = new Employee();
		suresh.setFirstName("Akash");
		suresh.setLastName("Chopra");
		suresh.setEmail("akash@gmail.com");

		this.employeeRepository.save(Akash);

		
		User vinay = new User();
		vinay.setUsername("vinay");
		vinay.setPassword(this.passwordEncoder.encode("welcome"));
		vinay.setEmailAddress("vinay@gmail.com");
			
		Role vinayRole = new Role();
		vinayRole.setRoleName("ADMIN");
		
		vinayRole.setUser(vinay);
		vinay.addRole(vinayRole);
		
		User rakesh = new User();
		rakesh.setUsername("rakesh");
		rakesh.setPassword(this.passwordEncoder.encode("welcome"));
		rakesh.setEmailAddress("rakesh@gmail.com");
		
		Role rakeshRole = new Role();
		rakeshRole.setRoleName("MANAGER");
		
		rakeshRole.setUser(rakesh);
		rakesh.addRole(rakeshRole);

		User kiran = new User();
		kiran.setUsername("kiran");
		kiran.setPassword(this.passwordEncoder.encode("welcome"));
		kiran.setEmailAddress("kiran@gmail.com");
		
		
		Role kiranRole = new Role();
		kiranRole.setRoleName("USER");
		
		kiranRole.setUser(kiran);
		kiran.addRole(kiranRole);
		
		
		
		
		this.userRepository.save(kiran);
		this.userRepository.save(vinay);
		this.userRepository.save(rakesh);
		
		
	}
}
