package multithreading;

public class Question7 {
    public static void main(String[] args) {
        Transaction t=new Transaction();
        MyThread1 ob1=new MyThread1(t);
        MyThread2 ob2=new MyThread2(t);
        ob1.start();
        ob2.start();
    }
}
class Transaction{
    int flag=0,data=0;
    synchronized void submit(){
        flag=1;
        try{
            Thread.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data=10000;
        System.out.println("Value submitted");
        notify();

    }
    synchronized int withdraw(){
        if(flag==0){
            try{
                System.out.println("Wait multithreading lock");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
 class MyThread1 extends Thread{
    Transaction s;
    MyThread1(Transaction t){
        s=t;
    }


      @Override
      public void run() {
          System.out.println(s.withdraw());
      }
  }


class MyThread2 extends Thread{
    Transaction s;
    MyThread2(Transaction t){
        s=t;
    }


    @Override
    public void run() {
        s.submit();
    }
}