package com.puffer.shopify.common.enums;

public enum ForTypeEnum {
    /**
     * 适应人群
     */
    MOM("mom", "母亲"),
    DAD("dad", "父亲"),
    TEACHER("teacher", "老师"),
    OHTER("other", "其他");

    private String value;
    private String description;

    ForTypeEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ForTypeEnum from(String value) {
        for (ForTypeEnum forTypeEnum : ForTypeEnum.values()) {
            if (forTypeEnum.getValue().equals(value)) {
                return forTypeEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
