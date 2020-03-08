package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This class is used in versioning.It take name,email,id and age in its constructor.")
public class UserDeatils {
    String name;
    String email;
    Integer id;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserDeatils() {
    }

    public UserDeatils(String name, String email, Integer id, Integer age) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.age = age;
    }
}
