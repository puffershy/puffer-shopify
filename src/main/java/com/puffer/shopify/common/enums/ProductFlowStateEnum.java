package com.puffer.shopify.common.enums;

public enum ProductFlowStateEnum {

    /**
     * 初始化
     */
    INIT(0),

    /**
     * 待更新标题
     */
    TO_UPDATE_TITLE(2),

    /**
     * 待更新描述
     */
    TO_UPDATE_DESCRIPTION(3),

    /**
     * 待上传
     */
    TO_UPLOAD(5),

    /**
     * 待更新图片
     */
    TO_UPDATE_IMG(1),

    /**
     * 上传成功
     */
    UPLOAD_SUCCESS(10),
    UPDATE_FROM_SHOPIFY(11);

    private int value;

    ProductFlowStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
