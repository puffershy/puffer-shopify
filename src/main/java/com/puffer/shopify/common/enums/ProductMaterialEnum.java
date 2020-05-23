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
    Ceramic("陶瓷");

    private String chinese;

    ProductMaterialEnum(String chinese) {
        this.chinese = chinese;
    }

    public String getChinese() {
        return chinese;
    }
}
