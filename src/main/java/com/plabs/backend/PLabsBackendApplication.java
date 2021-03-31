package com.plabs.backend;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class PLabsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PLabsBackendApplication.class, args);
    }


}
