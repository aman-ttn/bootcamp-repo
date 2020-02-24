//Create and access default and static method of an interface.
package program;
interface Java8{
    default void defaultMethod(){
        System.out.println("This is default");
    }
    static void staicMethod(){
        System.out.println("This is static");
    }
}
public class Question6DefaultStaticMethod implements Java8{
    public static void main(String[] args) {
        Question6DefaultStaticMethod obj=new Question6DefaultStaticMethod();
        obj.defaultMethod();
        Java8.staicMethod();
    }

}
