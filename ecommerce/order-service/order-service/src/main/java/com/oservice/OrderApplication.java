package com.oservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackageClasses = {com.coviam.model.Order.class,com.coviam.model.Item.class})
@EntityScan(basePackageClasses = {com.coviam.model.Order.class,com.coviam.model.Item.class})
@ComponentScan(basePackages = {"com.oservice"})
@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}

