package com.roopy.commonservice.api.dto;

public enum PaymentStatus {
    CREATED("10"),
    CANCELD("20"),
    COMPLETED("30");

    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
