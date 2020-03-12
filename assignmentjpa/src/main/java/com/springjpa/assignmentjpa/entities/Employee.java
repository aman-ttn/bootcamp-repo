package com.springjpa.assignmentjpa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

//(1) Create an Employee Entity which contains following fields
//        Name
//        Id
//        Age
//        Location

@Entity

public class Employee {
    @Id
    private int id;
    private int age;
    private String name;
    private String location;

    public Employee(int age, String name, String location) {
        this.age = age;
        this.name = name;
        this.location = location;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Employee(int id, int age, String name, String location) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.location = location;
    }
}
