//Find common elements between two arrays.
package program;

import java.util.Scanner;

public class Question5 {
    static int count=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the size of first array");
        int size1=sc.nextInt();
        int a[]=new int[size1];
        System.out.println("Input first array");
        for(int i=0;i<size1;i++){
            a[i]=sc.nextInt();
        }
        System.out.println("Input the size of second array");
        int size2=sc.nextInt();
        int b[]=new int[size2];
        System.out.println("Input second array");
        for(int i=0;i<size2;i++){
            b[i]=sc.nextInt();
        }
        int freq[]=new int[1000000];
        for(int i=0;i< a.length;i++){
            freq[a[i]]++;
        }
        for(int i=0;i<size2;i++){
            if(freq[b[i]] > 0){
                System.out.println("The duplicates elements are:"+b[i]);
                freq[b[i]]=0;
            }
        }

    }

}
