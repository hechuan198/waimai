package com.hechuan.waimai.util;

public enum commonEnum {
    ;


    private Integer status;
    private String message;

    commonEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
