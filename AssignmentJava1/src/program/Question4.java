// Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters, Digits And Other Special Characters In A String
package program;
import java.util.Scanner;
public class Question4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the String");
        String str = sc.nextLine();
        int l=str.length()-1;
        int len=l;
        int upper=0,lower=0,dig=0,spl=0;
        while(l>-1){
            char ch=str.charAt(l);
            if(Character.isUpperCase(ch)){
                upper ++;
            }
            else if(Character.isLowerCase(ch)){
                lower++;
            }
            else if(ch>=48 && ch<=57){
                dig++;
            }
            else{
                spl++;
            }
            l--;
        }
        double uper=(double)upper/len*100;
        double lper=(double)lower/len*100;
        double dper=(double)dig/len*100;
        double sper=(double)spl/len*100;
        System.out.println("The frequency of uppercase character: "+upper+" and its percentage is: "+uper);
        System.out.println("The frequency of lowercase character: "+lower+" and its percentage is: "+lper);
        System.out.println("The frequency of digit character: "+dig+" and its percentage is: "+dper);
        System.out.println("The frequency of special character: "+spl+" and its percentage is: "+sper);
    }
}
