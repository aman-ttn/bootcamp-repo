package com.bootcamp.springassignment.springFrameworkAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringFrameworkAssignmentApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext=SpringApplication.run(SpringFrameworkAssignmentApplication.class, args);
		ManagerQuestion3 managerQuestion3=applicationContext.getBean(ManagerQuestion3.class);
		managerQuestion3.manageWork();


	}

}
