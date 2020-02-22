// WAP to show object cloning in java using constructor .
package program;

public class Question5b {
    private  String name;
    private  int id;

    Question5b(String name, int id){
        this.name=name;
        this.id=id;
    }
    Question5b(Question5b q){
        name=q.name;
        id=q.id;
    }
    public static void main(String[] args) {
    Question5b ob1=new Question5b("Ankit",22);
    Question5b ob2=new Question5b(ob1);
        System.out.println("The data of object 1");
        System.out.println("Id:"+ob1.id);
        System.out.println("Name:"+ob1.name);
        System.out.println("The data of object 2");
        System.out.println("Id:"+ob2.id);
        System.out.println("Name:"+ob2.name);
    }
}
