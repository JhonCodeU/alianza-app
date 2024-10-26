package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.boot.controller", "com.boot.service"})
public class AlianzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlianzaApplication.class, args);
    }
}