package com.example.registerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class RegisterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterAppApplication.class, args);
	}

}
