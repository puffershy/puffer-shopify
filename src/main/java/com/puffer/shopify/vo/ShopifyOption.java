package com.puffer.shopify.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author puffer
 * @date 2020年01月06日 17:36:24
 * @since
 */
@Data
public class ShopifyOption {
    private String id;
    @JSONField(name = "product_id")
    private String productId;
    private String name;
    private int position;
    private List<String> values = new LinkedList<>();
}
