package com.puffer.shopify.common.enums;


/**
 * 产品状态
 *
 * @author puffer
 * @date 2020年05月23日 14:52:35
 * @since 1.0.0
 */

public enum ProductStateEnum {

    /**
     * 无效
     */
    DELETE(0),

    /**
     * 有效
     */
    EFFECTIVE(1);

    private int value;

    ProductStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
