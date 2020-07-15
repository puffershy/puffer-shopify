package com.puffer.shopify.common.enums;

public enum ShopifyMetafieldKeyEnum {
    /**
     * page title
     */
    TITLE_TAG("title_tag"),

    /**
     * page description
     */
    DESCRIPTION_TAG("description_tag");

    private String value;

    ShopifyMetafieldKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ShopifyMetafieldKeyEnum from(String value) {
        for (ShopifyMetafieldKeyEnum shopifyMetafieldKeyEnum : ShopifyMetafieldKeyEnum.values()) {
            if (shopifyMetafieldKeyEnum.getValue().equals(value)) {
                return shopifyMetafieldKeyEnum;
            }
        }

        return null;
    }
}
