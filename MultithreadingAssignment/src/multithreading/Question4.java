package multithreading;

class Mythread {
    int x, y;

    void add(int a, int b) {
        System.out.println("Hye");
        System.out.println("Bye");
        synchronized (this) {
            int s = a + b;
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Addition=" + s);
        }
    }
}
class Mythread1 extends Thread{
    MyThread q;
    Mythread1(MyThread o){
        q=o;
    }
    public void run(){
        q.add(4,5);
    }
}

class Mythread2 extends Thread{
    MyThread q;
    Mythread2(MyThread o){
        q=o;
    }
    public void run(){
        q.add(8,6);
    }
}
public class Question4{
    public static void main(String[] args) {
        MyThread ob=new MyThread();
        Thread1 t1=new Thread1(ob);
        Thread2 t2=new Thread2(ob);
        t1.start();
        t2.start();
    }
}