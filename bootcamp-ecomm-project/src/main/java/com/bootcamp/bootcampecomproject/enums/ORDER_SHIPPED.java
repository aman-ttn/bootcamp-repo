package com.bootcamp.bootcampecomproject.enums;

public enum ORDER_SHIPPED {
    DELIVERED("Delivered");
    private String value;
    ORDER_SHIPPED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
