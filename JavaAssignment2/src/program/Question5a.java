// WAP to show object cloning in java using cloneable .
package program;

public class Question5a implements Cloneable {
    private final int age;
    private String name;

    Question5a(String name, int age){
        this.name=name;
        this.age=age;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Question5a ob1=new Question5a("Aman",18);
        Question5a ob2=(Question5a) ob1.clone();
        System.out.println("Name:"+ob2.name);
        System.out.println("Age:"+ob2.age);
    }
}
