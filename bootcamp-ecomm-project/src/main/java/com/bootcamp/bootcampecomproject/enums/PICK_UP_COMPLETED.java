package com.bootcamp.bootcampecomproject.enums;

public enum PICK_UP_COMPLETED {
    REFUND_INITIATED("Refund Initiated");
    private String value;
    PICK_UP_COMPLETED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
