//Write a program to find the number of occurrences of a character in a string without using loop?
package program;
import java.util.Scanner;
public class Question3 {
    static int count=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the String");
        String str = sc.nextLine();
        System.out.println("Input the Character to check its occurence");
        char ch = sc.next().charAt(0);
        System.out.println(charCount(str,str.length()-1,ch));
    }
    public static int charCount(String str,int length,char ch){
        if(length>-1){
            if(str.charAt(length)==ch){
                count++;
            }
            charCount(str,length-1,ch);
        }
        return count;
    }
}
