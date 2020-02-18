// Write a program to reverse a string and remove character from index 4 to index 9 from the reversed string using String Buffer
package program;

import java.util.Scanner;

public class Question8 {
    public static void main(String[] args) {
        StringBuffer str=new StringBuffer();
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the string");
        str.append(sc.nextLine());
        str.reverse();
        System.out.println(str.delete(4,10));
    }
}
