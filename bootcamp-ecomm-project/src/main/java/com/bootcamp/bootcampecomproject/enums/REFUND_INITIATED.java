package com.bootcamp.bootcampecomproject.enums;

public enum REFUND_INITIATED {
    REFUND_COMPLETED("Refund Completed");
    private String value;
    REFUND_INITIATED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
