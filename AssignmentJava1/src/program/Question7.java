//Write a program to print your Firstname,LastName & age using static block,static method & static variable respectively
package program;

public class Question7 {
    static int age=18;
    static{
        System.out.println("Vinay");;
    }
    public static String middleName(){
        String mname="Parida";
        return mname;
    }

    public static void main(String[] args) {
        System.out.println(Question7.middleName()+"\n"+age);
    }
}
