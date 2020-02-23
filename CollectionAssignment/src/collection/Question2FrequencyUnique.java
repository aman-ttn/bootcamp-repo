//Write a method that takes a string and returns the number of unique characters in the string.
package collection;

import java.util.*;

public class Question2FrequencyUnique {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input the string");
        String  str=scanner.nextLine();
        char ch[]=(str.toLowerCase()).toCharArray();
        ArrayList<Character> l1=new ArrayList<Character>();
        for (char c:ch) {
            l1.add(c);
        }
        System.out.println("The frequency of unique character is "+freqUnique(l1));
    }
    public static int freqUnique(ArrayList<Character> l1){
        int count=0;
        for (char c:l1) {
            if(Collections.frequency(l1,c)==1){
                count++;
            }
        }
        return count;
    }
}
