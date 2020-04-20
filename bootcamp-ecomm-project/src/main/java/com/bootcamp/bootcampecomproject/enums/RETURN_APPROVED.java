package com.bootcamp.bootcampecomproject.enums;

public enum RETURN_APPROVED {
    PICK_UP_INITIATED("Pick up initiated");
    private String value;
    RETURN_APPROVED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
