package com.bootcamp.assignmentjpa2.dto;

public class EmployeeIDFirstNameAndAge {
    private Integer id;
    private String firstName;
    private Integer age;

    public EmployeeIDFirstNameAndAge(Integer id, String firstName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
