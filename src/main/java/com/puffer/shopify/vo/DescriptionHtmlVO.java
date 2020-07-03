package com.puffer.shopify.vo;

import com.puffer.shopify.common.enums.ProductSafeLevelEnum;
import lombok.Data;

/**
 * 描述格式实体
 *
 * @author puffer
 * @date 2020年07月03日 10:03:42
 * @since 1.0.0
 */
@Data
public class DescriptionHtmlVO {
    /**
     * 描述
     */
    private String description;
    /**
     * 容量
     */
    private String capacity;
    /**
     * 包含清单：1*Mug, 1*Spoon
     */
    private String include;

    /**
     * 安全级别，参考{@link ProductSafeLevelEnum}
     */
    private String safe;
}
