package com.puffer.shopify.vo.shopify;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 元字段
 *
 * @author puffer
 * @date 2020年07月15日 14:33:44
 * @since 1.0.0
 */
@Data
public class ShopifyMetafield {

    private String id;
    private String namespace;
    /**
     * title_tag   seo标题
     * description_tag  seo描述
     */
    private String key;

    /**
     * 值
     */
    private String value;

    /**
     * 值类型
     */
    @JSONField(name = "value_type")
    private String valueType;
}
