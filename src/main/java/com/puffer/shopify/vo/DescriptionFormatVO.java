package com.puffer.shopify.vo;

import lombok.Data;

/**
 * 描述内容格式实体
 *
 * @author puffer
 * @date 2020年07月03日 10:33:53
 * @since 1.0.0
 */
@Data
public class DescriptionFormatVO {
    /**
     * 杯子标题
     */
    private String title;

    /**
     * 杯子制作材料
     */
    private String material;

    /**
     * 引用文字
     */
    private String quote;
    /**
     * 使用场景
     */
    private String sceneFor;
}
