package com.bootcamp.bootcampecomproject.enums;

public enum RETURN_REJECTED {
    CLOSED("Closed");
    private String value;
    RETURN_REJECTED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
