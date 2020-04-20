package com.bootcamp.bootcampecomproject.enums;

public enum ORDER_REJECTED {
    REFUND_INITIATED("Refund Initiated"),
    CLOSED("Closed");
    private String value;
    ORDER_REJECTED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
