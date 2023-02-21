package com.roopy.commonservice.api.dto;

public enum OrderStatus {
    CREATED("10"),
    CANCELD("20"),
    COMPLETED("30"),
    APPROVED("40");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
