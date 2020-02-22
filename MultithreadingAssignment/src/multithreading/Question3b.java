//Write a program using synchronization method
package multithreading;

class MyThread {
    int x,y;
    synchronized void add(int a,int b){
        System.out.println("Hye");
        System.out.println("Bye");
        int s=a+b;
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Addition="+s);
    }
}

class Thread1 extends Thread{
    MyThread q;
    Thread1(MyThread o){
        q=o;
    }
    public void run(){
        q.add(4,5);
    }
}

class Thread2 extends Thread{
    MyThread q;
    Thread2(MyThread o){
        q=o;
    }
    public void run(){
        q.add(8,6);
    }
}
public class Question3b{
    public static void main(String[] args) {
        MyThread ob=new MyThread();
        Thread1 t1=new Thread1(ob);
        Thread2 t2=new Thread2(ob);
        t1.start();
        t2.start();
    }
}