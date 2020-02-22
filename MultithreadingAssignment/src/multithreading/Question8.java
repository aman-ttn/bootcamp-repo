//Write a program to demonstrate the use of semaphore.
package multithreading;
import java.util.concurrent.*;


class MythreadNew extends Thread
{
    Semaphore sem;
    String userName;
    public MythreadNew(Semaphore sem, String userName)
    {
        super(userName);
        this.sem = sem;
        this.userName = userName;
    }

    @Override
    public void run() {
        if(userName.equals("A"))
        {
            System.out.println("Starting " + userName);
            try
            {
                System.out.println(userName + " is waiting for a resource.");

                sem.acquire();

                System.out.println(userName + " gets a resource.");
                for(int i=0; i < 5; i++)
                {

                    System.out.println(userName + ": " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
            System.out.println(userName + " releases the resource.");
            sem.release();
        }

        else
        {
            System.out.println("Starting " + userName);
            try
            {
                System.out.println(userName + " is waiting for a resource.");
                sem.acquire();

                System.out.println(userName + " gets a resource.");
                for(int i=0; i < 5; i++)
                {

                    System.out.println(userName + ": " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
            System.out.println(userName + " releases the resource.");
            sem.release();
        }
    }
}
public class Question8
{
    public static void main(String args[]) throws InterruptedException
    {
        Semaphore sem = new Semaphore(1);
        MythreadNew t1 = new MythreadNew(sem, "A");
        MythreadNew t2 = new MythreadNew(sem, "B");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
} 
