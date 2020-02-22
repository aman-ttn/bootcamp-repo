//Write a program which creates deadlock between 2 threads
package multithreading;
public class Question10 {

    static Question10 ob1 = new Question10();
    static Question10 ob2 = new Question10();

    private static class ThreadName1 extends Thread {
        public void run() {
            synchronized (ob1) {
                System.out.println("Thread 1: Has  ObjectLock1");
            try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for ObjectLock 2");
            synchronized (ob2) {
                    System.out.println("Thread 1: No DeadLock");
                }
            }
        }
    }
    private static class ThreadName2 extends Thread {
        public void run() {
            synchronized (ob2) {
                System.out.println("Thread 2: Has  ObjectLock2");
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for ObjectLock 1");
                synchronized (ob1) {
                    System.out.println("Thread 2: No DeadLock");
                }
            }
        }
    }

    public static void main(String args[]) {
        ThreadName1 thread1 = new ThreadName1();
        ThreadName2 thread2 = new ThreadName2();
        thread1.start();
        thread2.start();
    }
}