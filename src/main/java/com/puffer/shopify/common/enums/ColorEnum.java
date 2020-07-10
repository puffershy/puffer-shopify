package com.puffer.shopify.common.enums;

public enum ColorEnum {
    /**
     * 白色
     */
    WHITE("white", "白色"),
    BLACK("black", "黑色");;

    private String value;
    private String description;

    ColorEnum(String value, String description) {
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
