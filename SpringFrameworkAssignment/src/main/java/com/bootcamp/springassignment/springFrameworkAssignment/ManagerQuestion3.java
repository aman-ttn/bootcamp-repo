package com.bootcamp.springassignment.springFrameworkAssignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagerQuestion3 {
//Question 3)-Use @Compenent and @Autowired annotations to in Loosely Coupled code for dependency management
    @Autowired
    private WorkerQuestion3 workerQuestion3;
//Question 6)-Perform Constructor Injection in a Spring Bean.
    ManagerQuestion3(WorkerQuestion3 workerQuestion3){
        this.workerQuestion3=workerQuestion3;
    }
    void manageWork(){
        workerQuestion3.doWork();
    }

}
