package com.bootcamp.bootcampecomproject.enums;

public enum DELIVERED {
    RETURN_REQUESTED("Return Requested"),
    CLOSED("Closed");
    private String value;
    DELIVERED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
