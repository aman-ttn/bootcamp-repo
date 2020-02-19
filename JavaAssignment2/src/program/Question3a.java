//WAP to produce NoClassDefFoundError and ClassNotFoundException exception.
package program;

public class Question3a {
    public static void main(String[] args) {
        try {
            A a=new A();
        }

        catch(NoClassDefFoundError ex){
            ex.printStackTrace();
        }
    }
}
class A{
    String z="Hi I am class A";
}
