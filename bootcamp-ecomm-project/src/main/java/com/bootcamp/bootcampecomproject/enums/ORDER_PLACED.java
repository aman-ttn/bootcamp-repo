package com.bootcamp.bootcampecomproject.enums;

public enum  ORDER_PLACED {
    CANCELLED("Order Cancelled"),
    ORDER_CONFIRMED("Order Confirmed"),
    ORDER_REJECTED("Order Rejected");
    private String value;
    ORDER_PLACED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
