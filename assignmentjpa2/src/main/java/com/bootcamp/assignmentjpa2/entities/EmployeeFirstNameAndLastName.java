package com.bootcamp.assignmentjpa2.entities;

public class EmployeeFirstNameAndLastName {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeFirstNameAndLastName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
