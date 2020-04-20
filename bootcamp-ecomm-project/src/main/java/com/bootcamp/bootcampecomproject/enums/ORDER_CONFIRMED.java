package com.bootcamp.bootcampecomproject.enums;

public enum ORDER_CONFIRMED {
    CANCELLED("Cancelled"),
    ORDER_SHIPPED("Order shipped");
    private String value;
    ORDER_CONFIRMED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
