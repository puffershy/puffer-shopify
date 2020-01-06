package com.puffer.shopify.common.enums;

/**
 * shopify 分页link rel枚举
 *
 * @author puffer
 * @date 2020年01月06日 19:21:13
 * @since 1.0.0
 */
public enum ShopifyRelEnum {

    /**
     * 下一页
     */
    NEXT("next"),

    /**
     * 上一页
     */
    PREVIOUS("previous");

    private String value;

    ShopifyRelEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isValue(String value) {
        return getValue().equals(value);
    }
}
