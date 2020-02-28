//Write a program to sort Employee objects based on highest salary using Comparator.
// Employee class{ Double Age; Double Salary; String Name
package collection;

import java.util.*;
class Person {
    private String name;
    private double age;
    private double salary;
    public Person(String name,double age,double salary){
        this.name=name;
        this.age=age;
        this.salary=salary;
    }
    public String toString(){
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double getAge() {
        return age;
    }


}

public class Question5SortSalary {
    public static void main(String[] args) {
        List<Person> list=new ArrayList<Person>();
        //addElement(list);
        list.add(new Person("John",29.3,3998.4));
        list.add(new Person("Aman",87.3,6876.2));
        list.add(new Person("Ankit",45,79833.3));
        list.add(new Person("Vinay",45.3,797));
        list.add(new Person("Anuj",77,68768.3));
        Comparator<Person> personComparator=new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return (int)p2.getSalary()-(int)p1.getSalary();
            }
        };
        Collections.sort(list,personComparator);
        showElement(list);
        System.out.println();

    }
    public static void addElement(Collection<Person> col){
        col.add(new Person("John",29.3,3998.4));
        col.add(new Person("Aman",87.3,6876.2));
        col.add(new Person("Ankit",45,79833.3));
        col.add(new Person("Vinay",45.3,797));
        col.add(new Person("Anuj",77,68768.3));
    }
    public static void showElement(Collection<Person> col){
        for (Person element:col) {
            System.out.println("Name:"+element.toString()+" | Salary : "+element.getSalary()+" | Age : " + element.getAge());
        }
    }
}
