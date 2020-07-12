package com.puffer.shopify.common.enums;

import org.apache.commons.lang3.StringUtils;

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

    public static ColorEnum fromValue(String value){
        if (StringUtils.isBlank(value)) {
            //如果传餐为空，则默认返回null
            return null;
        }

        for (ColorEnum colorEnum : ColorEnum.values()) {
            if (colorEnum.getValue().equals(value)) {
                return colorEnum;
            }
        }

        return null;
    }
}
