//Create a simple REST ful service in Spring Boot which returns the Response "Welcome to spring boot".

package com.springAssignment.RestfulWebServicesPart1.springAssignment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeSpringBoot {
    @GetMapping(path="/welcome")
    public String  printWelcome(){
        return "Welcome to Spring Boot";
    }
}
