package com.puffer.shopify.common.enums;

/**
 * 模板枚举
 *
 * @author puffer
 * @date 2020年07月03日 11:42:33
 * @since 1.0.0
 */
public enum TemplateEnum {
    DESCRIPTION_HTML("001");

    private String value;

    TemplateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
