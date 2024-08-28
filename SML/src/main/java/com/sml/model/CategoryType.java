package com.sml.model;

public enum CategoryType {
    NOTICE(1, 10),
    SAFETY(11, 20);

    private final int minCode;
    private final int maxCode;

    CategoryType(int minCode, int maxCode) {
        this.minCode = minCode;
        this.maxCode = maxCode;
    }

    public int getMinCode() {
        return minCode;
    }

    public int getMaxCode() {
        return maxCode;
    }

    public static CategoryType getTypeByCode(int code) {
        for (CategoryType type : values()) {
            if (code >= type.minCode && code <= type.maxCode) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid category code: " + code);
    }
}