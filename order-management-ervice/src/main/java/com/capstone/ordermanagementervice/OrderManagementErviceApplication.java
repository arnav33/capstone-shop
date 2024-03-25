package com.capstone.ordermanagementervice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.capstone.ordermanagementervice.repositories")
public class OrderManagementErviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementErviceApplication.class, args);
	}

}
