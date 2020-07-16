package com.puffer.shopify.common.enums;

/**
 *  内容类型
 *
 * @author buyi
 * @date 2020年07月16日 19:21:53
 * @since 7.17.4
 */
public enum ContentTypeEnum {
    MADE("made"),
    FOR("for");

    private String value;

    ContentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
