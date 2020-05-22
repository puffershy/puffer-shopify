package com.puffer.shopify.common.enums;

public enum ProductFlowStateEnum {

    /**
     * 待上传
     */
    TO_UPLOAD(0);

    private int value;

    ProductFlowStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
