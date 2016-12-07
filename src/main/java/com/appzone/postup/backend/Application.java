package com.appzone.postup.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Mohamed Morsy
 *
 * Springboot Application Configuration
 */
@SpringBootApplication
public class Application {

    /**
     *
     * @param args
     *
     * application starting point
     */
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }
  

}
