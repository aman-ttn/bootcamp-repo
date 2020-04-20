package com.bootcamp.bootcampecomproject.enums;

public enum RETURN_REQUESTED {
    RETURN_REJECTED("Return Rejected"),
    RETURN_APPROVED("Return Approved");
    private String value;
    RETURN_REQUESTED(String value){
        this.value=value;
    }
}
