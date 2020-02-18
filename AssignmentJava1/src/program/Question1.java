//Write a program to replace a substring inside a string with other string ?
package program;
import java.util.*;
public class Question1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Input the String");
        String str = sc.nextLine();
        System.out.println("Input the subtring to be replaced");
        String sub_str1 = sc.nextLine();
        System.out.println("Input the subtring that will replaced");
        String sub_str2 = sc.nextLine();
        System.out.println(str.replace(sub_str1, sub_str2));
    }
}
