package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneBookApplication {


	public static void main(String[] args) {
		SpringApplication.run(PhoneBookApplication.class, args);
//		new ContactService().populate();
	}
}
