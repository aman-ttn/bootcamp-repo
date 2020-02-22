//WAP showing try, multi-catch and finally blocks.
package program;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Question6 {
    public static void main(String[] args) {
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Input the size of array");
            int size=sc.nextInt();
            int sum=0;
            System.out.println("Input the array elements");
            int a[]=new int[size];
            for (int i = 0; i <size ; i++) {
                a[i]=sc.nextInt();
            }
            for (int i = 0; i <size ; i++) {
                sum+=a[i];
            }
            int num=a[5]/a[0];
        }
        catch(ArithmeticException e) {
            e.printStackTrace();
        }
        catch(InputMismatchException e) {
            e.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            System.out.println("Finally executing");
        }
    }
}
