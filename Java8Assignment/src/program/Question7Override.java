// Override the default method of the interface.
package program;
interface MyInter {
    default void defaultMethod() {
        System.out.println("This is default");
    }
}
public class Question7Override implements MyInter {
    void MyInter(){
        System.out.println("Now default is overridden");
    }

    public static void main(String[] args) {
        Question7Override obj=new Question7Override();
        obj.MyInter();
    }
}
