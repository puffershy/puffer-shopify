package com.puffer.shopify.common.enums;

public enum KeywordTypeEnum {
    // quote-引诉，shaped-形状的，mom-母亲，dad-父亲，changing-热力
    QUOTE("quote", "引用"),
    SHAPED("shaped", "引用"),
    MOM("mom", "母亲"),
    DAD("dad", "父亲"),
    CHANGING("changing", "热力"),
    CHRISTMAS("christmas","圣诞节"),
    OTHER("other", "其他"),
    ;

    private String value;
    private String description;

    KeywordTypeEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
