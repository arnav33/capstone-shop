package com.capstone.productcatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableElasticsearchRepositories("com.capstone.productcatalogservice.repository")
@EnableJpaRepositories("com.capstone.productcatalogservice.repository")
@ComponentScan({
		"com.capstone.productcatalogservice.controller",
		"com.capstone.productcatalogservice.service",
		"com.capstone.productcatalogservice.wrapper"
})
@EntityScan("com.capstone.productcatalogservice.entity")
@SpringBootApplication
public class ProductCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogServiceApplication.class, args);
	}

}
