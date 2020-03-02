package com.bootcamp.springassignment.springFrameworkAssignment.LooselyCoupledQuestion2;

public class ManagerQuestion2 {
    private WorkerQuestion2 lazyWorkerQuestion2;
    ManagerQuestion2(LazyWorkerQuestion2 lazyWorkerQuestion2){
        this.lazyWorkerQuestion2=lazyWorkerQuestion2;
    }
    void manageWork(){
        lazyWorkerQuestion2.doWork();
    }
}
