//Write a program to sort the Student objects based on Score ,
// if the score are same then sort on First Name .
// Class Student{ String Name; Double Score; Double Age
package collection;

import java.util.*;
class Student implements Comparable<Student>{
    private String name;
    private double age;
    private double score;
    public Student(String name,double age,double score){
        this.name=name;
        this.age=age;
        this.score=score;
    }
    public String toString(){
        return name;
    }

    public double getScore() {
        return score;
    }

    public double getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.age, age) == 0 &&
                Double.compare(student.score, score) == 0 &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, score);
    }

    @Override
    public int compareTo(Student student) {
        if(score > student.score){
            return -1;
        }
        else if(score < student.score){
            return 1;
        }
        else{
            return name.compareTo(student.name);
        }
    }

}

public class Question6SortStudent {
    public static void main(String[] args) {
        List<Student> list=new ArrayList<Student>();
        addElement(list);
        Collections.sort(list);
        showElement(list);
        System.out.println();

    }
    public static void addElement(Collection<Student> col){
        col.add(new Student("John",16,399.4));
        col.add(new Student("Aman",13,687.2));
        col.add(new Student("Ankit",15,399.4));
        col.add(new Student("Vinay",15,79));
        col.add(new Student("Anuj",17,687.3));
    }
    public static void showElement(Collection<Student> col){
        for (Student element:col) {
            System.out.println("Name:"+element.toString()+" | Score : "+element.getScore()+" | Age : " + element.getAge());
        }
    }
}
