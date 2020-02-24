//Using (instance) Method reference create and apply add and subtract method and using
// (Static)Method reference create and apply multiplication method for the functional interface created.
package program;
interface MethodInterface{
    public void show(int a,int b);
}
public class Question3InstanceStatic {
    public void add(int a, int b){
        System.out.println(a+b);
    }
    public void sub(int a, int b){
        System.out.println(a-b);
    }
    static void mul(int a, int b){
        System.out.println(a*b);
    }

    public static void main(String[] args) {
        MethodInterface methodInterface=new Question3InstanceStatic():: add;
        MethodInterface methodInterface1=new Question3InstanceStatic():: sub;
        MethodInterface methodInterface2=Question3InstanceStatic:: mul;
        methodInterface.show(4,5);
        methodInterface1.show(9,6);
        methodInterface2.show(9,6);
    }


}
