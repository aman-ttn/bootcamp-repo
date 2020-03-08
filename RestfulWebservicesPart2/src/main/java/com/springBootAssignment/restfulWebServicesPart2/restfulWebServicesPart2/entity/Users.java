package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This is User Class which takes id,name,age in its constructor")
public class Users {
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

    public Users(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    Integer id;
    String name;
    Integer age;
}
