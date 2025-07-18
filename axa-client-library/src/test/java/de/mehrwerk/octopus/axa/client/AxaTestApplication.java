package de.mehrwerk.octopus.axa.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "de.mehrwerk.octopus.axa")
public class AxaTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(AxaTestApplication.class, args);
    }
}
