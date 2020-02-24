//Create a functional interface whose method takes 2 integers and return one integer.
package program;
interface One{
    public int returnOne(int a,int b);
}
public class Question2ReturnOne {
    public static void main(String[] args) {

        One one=(e1,e2)-> e1;
        System.out.println(one.returnOne(4,5));
    }
}
