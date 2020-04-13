package com.bootcamp.bootcampecomproject.dtos;

import com.bootcamp.bootcampecomproject.entities.Label;

public class CustomerAddressDto {
    private String city;
    private String state;
    private String country;
    private String address;
    private Label label;
    private Integer zipCode;

    public CustomerAddressDto(String city, String state, String country, String address, Label label, Integer zipCode) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.address = address;
        this.label = label;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
