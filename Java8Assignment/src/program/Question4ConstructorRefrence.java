//Create an Employee Class with instance variables
// (String) name, (Integer)age, (String)city and get the instance of the Class using constructor reference
package program;
@FunctionalInterface
interface ConstructRef{
    Employee returnObject();
}
class Employee{
    String name,city;
    int age;
    Employee(String name,int age,String city){
        this.name=name;
        this.age=age;
        this.city=city;
    }
    Employee returnObject(){
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }
}
public class Question4ConstructorRefrence {

    public static void main(String[] args) {

        ConstructRef constructRef=new Employee("Aman",21,"Noida")::returnObject;
        System.out.println(constructRef.returnObject());
    }

}
