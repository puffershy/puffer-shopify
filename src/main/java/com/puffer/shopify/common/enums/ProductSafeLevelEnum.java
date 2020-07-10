package com.puffer.shopify.common.enums;

/**
 * 产品安全枚举
 *
 * @author puffer
 * @date 2020年07月03日 10:21:00
 * @since 1.0.0
 */
public enum ProductSafeLevelEnum {
    /**
     * 微波炉安全，洗碗机完全
     */
    BOTH_SAFE(1, "Microwave and dishwasher safe"),

    /**
     * 微波炉安全，洗碗机不安全
     */
    ONLY_MICROWAVE(2, "Microwave safe, but not dishwasher safe"),

    /**
     * 微波炉不安全，洗碗机不安全
     */
    BOTH_NOT(3, "Not microwave safe and not dishwasher safe"),
    /**
     * 只能洗碗机
     */
    ONLY_DISHWASHER(4, "Dishwasher safe");

    private int value;
    private String description;

    ProductSafeLevelEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据值转换成安全级别枚举，如果没有，则默认全不安全
     *
     * @param value
     * @return com.puffer.shopify.common.enums.ProductSafeLevelEnum
     * @author puffer
     * @date 2020年07月03日 10:28:06
     * @since 1.0.0
     */
    public static ProductSafeLevelEnum fromValue(int value) {
        for (ProductSafeLevelEnum safeLevelEnum : ProductSafeLevelEnum.values()) {
            if (safeLevelEnum.getValue() == value) {
                return safeLevelEnum;
            }
        }

        return ProductSafeLevelEnum.BOTH_NOT;
    }
}
