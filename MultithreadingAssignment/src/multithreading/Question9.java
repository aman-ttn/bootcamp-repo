//Write a program to demonstrate the use of CountDownLatch.
package multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Question9 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(3);
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Parent(latch));
        }
        latch.await();
        System.out.println("Completed");
    }
}
class Parent implements Runnable{
private CountDownLatch latch;
Parent(CountDownLatch latch){
    this.latch=latch;
}

    public void run() {
        System.out.println("Started");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
