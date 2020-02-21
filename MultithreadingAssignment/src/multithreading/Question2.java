package multithreading;

import java.util.Scanner;

public class Question2{
public volatile int counter=0;
    public static void main(String[] args) {

        T1 t1=new T1();
        t1.start();
        Scanner sc=new Scanner(System.in);
        sc.nextLine();
        t1.shutdown();
    }
}
class T1 extends Thread{
public boolean flag=true;

    @Override
    public void run() {
        while(flag){

            System.out.println("Thread is running.");
        }

    }
    public void shutdown(){
        flag=false;
    }
}
