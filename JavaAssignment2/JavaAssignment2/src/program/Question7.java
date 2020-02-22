//WAP to convert seconds into days, hours, minutes and seconds.
package program;

import java.util.Scanner;

public class Question7 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long sec=sc.nextLong();
        long days=sec/86400;
        sec=sec-(days*86400);
        long hours=sec/3600;
        sec=sec-(hours*3600);
        long mins=sec/60;
        sec=sec-(mins*60);
        System.out.println("Days:"+days+"\n"+"Hours"+hours+"\n"+"Minutes"+mins+"\n"+"Seconds:"+sec);
    }
}
