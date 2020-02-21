package multithreading;
class Demo1 implements Runnable{
    public void run() {
        for (int i = 0; i <10; i++) {
            System.out.println(i);
        }
    }
}
class Demo2 extends Thread{
    public void run(){
        System.out.println("Hye");
    }
}
class Demo3 extends Thread{
    public void run(){
        System.out.println("Bye");
    }
}
public class Question3 {
    public static void main(String[] args) {
        Demo1 ob=new Demo1();
        Thread t1=new Thread(ob);
        Thread t2=new Thread(ob);
        t1.start();
        t2.start();
        Demo2 ob2=new Demo2();
        ob2.start();
        Demo3 ob3=new Demo3();
        ob3.start();
    }
}

