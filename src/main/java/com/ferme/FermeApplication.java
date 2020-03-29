package com.ferme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ferme.*")
public class FermeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FermeApplication.class, args);
	}

}
