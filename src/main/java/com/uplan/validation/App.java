package com.uplan.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.uplan.validation")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
