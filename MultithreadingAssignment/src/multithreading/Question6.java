package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println("Even numbers:" + i);
            }
        }
    }
}
public class Question6 {
    public static void main(String[] args) {

        Odd ob1=new Odd();
        Even ob2=new Even();
//        Thread t1=new Thread(ob1);
//        Thread t2=new Thread(ob2);
        ExecutorService pool = Executors.newFixedThreadPool(2);


        pool.execute(ob1);

        pool.execute(ob2);
        pool.shutdown();

    }
}
