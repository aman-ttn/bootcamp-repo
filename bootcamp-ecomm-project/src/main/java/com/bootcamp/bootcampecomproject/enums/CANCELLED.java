package com.bootcamp.bootcampecomproject.enums;

public enum CANCELLED {
    REFUND_INITIATED("Refund Intiated"),
    CLOSED("Closed");
    private String value;
    CANCELLED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
