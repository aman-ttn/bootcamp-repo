//Write the following a functional interface and implement it using lambda:
//        (1) First number is greater than second number or not         Parameter (int ,int ) Return boolean
//        (2) Increment the number by 1 and return incremented value    Parameter (int) Return int
//        (3) Concatination of 2 string                                 Parameter (String , String ) Return (String)
//        (4) Convert a string to uppercase and return .                Parameter (String) Return (String)
package program;
interface Great{
    public boolean greater(int a,int b);
}
interface Incre{
    public int increNumber(int a);
}
interface Concat{
    public String concat(String str,String strn);
}
interface Upper{
    public String upperCase(String str);
}
public class Question1LambdaFunction {
    public static void main(String[] args) {
        Great greaterNumber=(e1,e2)-> e1>e2;
        System.out.println(greaterNumber.greater(4,5));
        Incre increnumber=(e3)->e3+1;
        System.out.println(increnumber.increNumber(5));
        Concat con=(e1,e2)-> e1.concat(e2);
        System.out.println(con.concat("Hello","Java"));
        Upper upper=(e1)->e1.toUpperCase();
        System.out.println(upper.upperCase("hello"));
    }

}
