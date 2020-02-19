//WAP to sorting string without using string Methods?.
package program;

public class Question2 {
    public char[] Sort(char arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {

                    char temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        return arr;
    }

    public static void main(String[] args) {
        Question2 ob=new Question2();
        String str="Helloworld";
        char ch[]=str.toCharArray();
        ch=ob.Sort(ch);
        for(char x:ch)
            System.out.print(x);
    }
}
