package com.coviam.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

/**
 * Created by coviam on 17/08/17.
 */

@EnableAutoConfiguration(exclude = {
        JpaRepositoriesAutoConfiguration.class
})
@SpringBootApplication
public class ModelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModelApplication.class);
    }
}
