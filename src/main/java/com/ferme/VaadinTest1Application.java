package com.ferme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ferme.*")
public class VaadinTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(VaadinTest1Application.class, args);
	}

}
