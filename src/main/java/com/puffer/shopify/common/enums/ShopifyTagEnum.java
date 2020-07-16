package com.puffer.shopify.common.enums;

public enum ShopifyTagEnum {

    /**
     * 杯子
     */
    MUG("mug"),
    /**
     * 新到
     */
    QUOTE_MUG("quote mug"),

    SHAPED_MUG("shaped mug"),

    ANIMAL_MUG("animal mug"),

    CHANGING_MUG("chaning mug"),

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
