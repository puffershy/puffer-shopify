package com.puffer.shopify.common.enums;

public enum KeywordSubTypeEnum {
    // quote-引诉，shaped-形状的，mom-母亲，dad-父亲，changing-热力
    CAT("cat", "猫"),
    INSPIRE("inspire", "鼓舞"),
    BIBLE("bible", "圣经");

    private String value;
    private String description;

    KeywordSubTypeEnum(String value, String description) {
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
