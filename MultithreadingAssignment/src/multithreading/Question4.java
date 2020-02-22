//Write a program to create a Thread pool of 2 threads where one Thread will print even numbers and other will print odd numbers.
package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Odd implements Runnable{
    @Override
    public void run() {
    for(int i=0;i<10;i++){
        if(i%2!=0){
            System.out.println("Odd numbers:"+i);
        }
    }
    }
}
class Even implements Runnable{
    synchronized public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println("Even numbers:" + i);
            }
        }
    }
}
public class Question4 {
    public static void main(String[] args) throws InterruptedException {

        Odd ob1=new Odd();
        Even ob2=new Even();

        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(ob1);
        pool.execute(ob2);
        pool.shutdown();

    }
}
