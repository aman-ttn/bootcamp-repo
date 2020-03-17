package com.assignmentjpa3.assignmentjpa3.entities;

import javax.persistence.Embeddable;

// 1)-Create a class Address for Author with instance variables streetNumber, location, State.

@Embeddable
public class Address {
    private Integer streetnumber;
    private String location;
    private String state;

    public Integer getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(Integer streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
