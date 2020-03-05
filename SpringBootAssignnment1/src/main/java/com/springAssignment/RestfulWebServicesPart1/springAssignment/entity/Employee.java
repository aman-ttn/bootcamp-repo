package com.springAssignment.RestfulWebServicesPart1.springAssignment.entity;

import javax.validation.constraints.Size;

public class Employee {

    private Integer id;

    @Size(min = 2,message = "Only two character in he Name is allowed.")
    private String name;
    private Integer age;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Employee(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
