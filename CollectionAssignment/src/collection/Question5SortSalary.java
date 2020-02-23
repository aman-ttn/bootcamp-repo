//Write a program to sort Employee objects based on highest salary using Comparator.
// Employee class{ Double Age; Double Salary; String Name
package collection;

import java.util.*;
class Person implements Comparable<Person>{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.age, age) == 0 &&
                Double.compare(person.salary, salary) == 0 &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary);
    }

    @Override
    public int compareTo(Person person) {
        if(salary > person.salary){
            return -1;
        }
        else if(salary < person.salary){
            return 1;
        }
        else{
            return 0;
        }
    }

}

public class Question5SortSalary {
    public static void main(String[] args) {
        List<Person> list=new ArrayList<Person>();
        addElement(list);
        Collections.sort(list);
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
