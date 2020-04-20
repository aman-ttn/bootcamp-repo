package com.bootcamp.bootcampecomproject.enums;

public enum PICK_UP_INITIATED {
    PICK_UP_COMPLETED("Pick up completed");
    private String value;
    PICK_UP_INITIATED(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
