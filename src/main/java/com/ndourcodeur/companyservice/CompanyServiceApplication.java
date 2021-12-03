package com.ndourcodeur.companyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CompanyServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CompanyServiceApplication.class, args);

		System.out.println("App started .....");
	}

}
