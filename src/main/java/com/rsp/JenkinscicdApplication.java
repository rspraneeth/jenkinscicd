package com.rsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JenkinscicdApplication {

    @GetMapping("/greetings/{name}")
    public String greetings(@PathVariable String name){
        return "Hello "+name+", Good day!";
    }

    public static void main(String[] args) {
        SpringApplication.run(JenkinscicdApplication.class, args);
    }

}
