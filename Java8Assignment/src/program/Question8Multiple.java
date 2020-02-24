//Implement multiple inheritance with default method inside  interface.
package program;
interface Inter1 {
    default void defaultMethod() {
        System.out.println("This is Inter1");
    }
}
interface Inter2 {
    default void defaultMethod() {
        System.out.println("This is Inter2");
    }
}
public class Question8Multiple implements Inter1,Inter2{



    public static void main(String[] args) {
        Question8Multiple obj=new Question8Multiple();
        obj.defaultMethod();

    }

    @Override
    public void defaultMethod() {
        System.out.println("This is now compulsory to overridden");
    }
}
