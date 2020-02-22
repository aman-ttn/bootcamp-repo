//Write a program to demonstrate sleep and join methods.
package multithreading;

public class Question6 {
    public static void main(String[] args) throws InterruptedException {
        New ob1=new New();
        New ob2=new New();
        ob1.start();

        ob1.join();
        ob2.start();
        ob2.join();
    }
}
class New extends Thread{
public static int counter=0;
    public void run() {
        for (int i = 0; i <5; i++) {
            System.out.println("Hello");
            System.out.println("Hi");
            System.out.println(counter++);

        }
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
