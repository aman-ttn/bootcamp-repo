//There is an array with every element repeated twice except one. Find that element
package program;

import java.util.Scanner;

public class Question6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the size of first array");
        int size= sc.nextInt();
        int arr[] = new int[size];
        System.out.println("Input first array");
        for(int i=0;i<size;i++){
            arr[i] = sc.nextInt();
        }

        for(int i=0;i<size;i++){
            int count =0;
            for(int j=0;j<size;j++){
                if(arr[i]==arr[j]){
                    count++;
                }
            }
            if(count==1){
                System.out.println("The single element is:"+arr[i]);
                break;
            }
        }

    }
}