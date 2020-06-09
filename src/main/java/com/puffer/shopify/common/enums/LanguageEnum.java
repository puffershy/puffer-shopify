package com.puffer.shopify.common.enums;

/**
 * 语言枚举
 *
 * @author buyi
 * @date 2020年06月09日 09:50:18
 * @since 1.0.0
 */
public enum LanguageEnum {

    /**
     * 英语
     */
    // ENGLISH(""),
    /**
     * 西班牙
     */
    SPAIN("es"),

    /**
     * 阿拉伯
     */
    ARAB("ar"),

    /**
     * 法语
     */
    FRENCH("fr"),

    /**
     * 德国
     */
    GERMAN("de"),

    /**
     * 意大利
     */
    ITALY("it");

    private String code;

    LanguageEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
