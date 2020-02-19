//WAP to create singleton class.
package program;
public class Question4 {
    public static void main(String[] args) {
        SingletonDemo ob = SingletonDemo.getObject( );
        ob.demoMethod( );
    }
}
class SingletonDemo {
    private static SingletonDemo singleton = new SingletonDemo( );
    private SingletonDemo() { }
    public static SingletonDemo getObject( ) {
        return singleton;
    }
    protected static void demoMethod( ) {
        System.out.println("Demo Method for singleton");
    }
}
