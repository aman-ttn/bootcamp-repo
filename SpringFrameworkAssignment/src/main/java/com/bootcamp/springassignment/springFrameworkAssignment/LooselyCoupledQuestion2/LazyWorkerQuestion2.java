package com.bootcamp.springassignment.springFrameworkAssignment.LooselyCoupledQuestion2;

public class LazyWorkerQuestion2 implements WorkerQuestion2 {
    @Override
    public void doWork() {
        System.out.println("Lazy Worker is working.");
    }
}
