package com.example.demo;

import com.example.demo.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibMenu();
	}

}

