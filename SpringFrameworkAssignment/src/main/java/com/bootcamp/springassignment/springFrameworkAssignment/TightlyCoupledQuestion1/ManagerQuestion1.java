package com.bootcamp.springassignment.springFrameworkAssignment.TightlyCoupledQuestion1;

public class ManagerQuestion1 {
    private LazyWorkerQuestion1 lazyWorker;
    private ExcellentWorkerQuestion1 excellentWorker;
    ManagerQuestion1(LazyWorkerQuestion1 lazyWorker, ExcellentWorkerQuestion1 excellentWorker){
        this.lazyWorker=lazyWorker;
        this.excellentWorker=excellentWorker;
    }
    public void manageWork(){
        lazyWorker.doWork();
        excellentWorker.doWork();
    }
}
