//Create a custom exception that do not have any stack trace.
package program;

import java.util.Scanner;

class MyException extends Exception{
    MyException(String msg,boolean myFlag){
        super(msg,null,myFlag,!myFlag);
    }
}
public class Question13 {
    public static void main(String[] args) throws MyException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your age");
        int age=sc.nextInt();
        try {
            if (age < 18)
                throw new MyException("Your are not eligible to vote",true);
            else
                System.out.println("You are eligible to vote");
        }
        catch(MyException ex){
            ex.printStackTrace();
        }

    }
}
