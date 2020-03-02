package com.bootcamp.springassignment.springFrameworkAssignment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//Question 3)-Use @Compenent and @Autowired annotations to in Loosely Coupled code for dependency management
// Question 4)-Demonstrate how you will resolve ambiguity while autowiring bean (Hint : @Primary)
@Component
@Primary
public class ExcellentWorkerQuestion3 implements WorkerQuestion3{
    @Override
    public void doWork() {
        System.out.println("Excellent Worker Working");
    }
}
