package com.lucianvaleanu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MaterialMinderBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialMinderBackendApplication.class, args);
    }

}
