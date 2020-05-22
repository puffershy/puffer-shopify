package com.puffer.shopify.common.enums;

public enum YesNoEnum {

    /**
     * 否
     */
    NO(0),
    /**
     * 是
     */
    YES(1);

    private int value;

    YesNoEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
