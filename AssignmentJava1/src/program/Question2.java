//Write a program to find the number of occurrences of the duplicate words in a string and print them ?
package program;
import java.util.*;
public class Question2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the String");
        String str = sc.nextLine();

        String Words[]=str.split(" ");
        for(int i=0;i<Words.length;i++){
            int freq=countOccur(Words[i],Words);
            if(freq>1 && Words[i]!=""){
                System.out.println(Words[i]);

            }
        }

    }
    public static int countOccur(String str,String[] Words){
        int count=0;
        for(int i=0;i<Words.length;i++){
            if(str.equalsIgnoreCase(Words[i])){
                count++;
                if(count>1){
                Words[i]="";}
            }
        }
        return count;
    }
}
