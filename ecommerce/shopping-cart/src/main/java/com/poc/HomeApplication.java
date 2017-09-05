package com.poc;

import com.poc.utilities.Mail;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration(exclude = {
		JpaRepositoriesAutoConfiguration.class
})
@SpringBootApplication
public class HomeApplication {

//	public static void main(String[] args) {
//		try (ConfigurableApplicationContext context =
//					 SpringApplication.run(Application.class, args)) {
//			LOG.trace("context: " + context);
//		}
//	}
	public static void main(String[] args) {

		SpringApplication.run(HomeApplication.class, args);
	}
}
