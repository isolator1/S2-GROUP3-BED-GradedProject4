package com.glearning.employees.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainText = "welcome";
		
		String encodedPassword = "$2a$10$BFM6SudGOBeey8O7JWQCueIFfDpVg1kv9qi0fm3xrwLx65ow1CavK";
		
		System.out.println(encodedPassword);
		
		System.out.println(passwordEncoder.matches(plainText, encodedPassword));
	}

}
