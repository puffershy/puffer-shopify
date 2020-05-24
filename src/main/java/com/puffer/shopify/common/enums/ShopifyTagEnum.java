package com.puffer.shopify.common.enums;

public enum ShopifyTagEnum {


    /**
     * 新到
     */
    NEW_ARRIVAL("New Arrival"),

    /**
     * 陶瓷
     */
    CERAMIC("Ceramic");

    private String value;

    ShopifyTagEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
