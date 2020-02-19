//WAP to read words from the keyboard until the word done is entered.
// For each word except done, report whether its first character is equal   to  its last character.
// For the required loop, use a
//do while statement
package program;

import java.util.Scanner;

public class Question8b {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the words");
        do{
            String st=sc.next();
            if(st.equals("done"))
                break;
            else
                System.out.println(checkFirstLast(st));
        }while(true);
    }
    public static String checkFirstLast(String st){
        if(st.charAt(0)==st.charAt(st.length()-1))
            return "The first and last character of word is equal";
        else
            return "The first and last character of word is not equal";
    }
}
