package com.puffer.shopify.common.enums;

/**
 * 材质枚举
 *
 * @author puffer
 * @date 2020年05月23日 14:51:09
 * @since 1.0.0
 */

public enum ProductMaterialEnum {
    /**
     * 咖啡杯
     */
    CERAMIC("Ceramic","陶瓷");

    private String value;

    private String decrition;

    ProductMaterialEnum(String value, String decrition) {
        this.value = value;
        this.decrition = decrition;
    }

    public String getValue() {
        return value;
    }

    public String getDecrition() {
        return decrition;
    }
}
