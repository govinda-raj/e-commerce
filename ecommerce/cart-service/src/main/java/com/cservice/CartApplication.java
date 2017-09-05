package com.cservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Entity;


@ComponentScan(basePackageClasses = {com.coviam.model.Cart.class})
@EntityScan(basePackageClasses = {com.coviam.model.Cart.class})
@ComponentScan(basePackages = {"com.cservice"})
@SpringBootApplication
public class CartApplication {
	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

}
