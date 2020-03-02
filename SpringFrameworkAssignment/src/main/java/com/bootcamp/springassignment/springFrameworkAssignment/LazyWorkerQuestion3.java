package com.bootcamp.springassignment.springFrameworkAssignment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//Question 3)-Use @Compenent and @Autowired annotations to in Loosely Coupled code for dependency management
@Component
public class LazyWorkerQuestion3 implements WorkerQuestion3 {
    @Override
    public void doWork() {
        System.out.println("Lazy Worker Working");
    }
}
