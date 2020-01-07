package com.puffer.shopify.vo;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author puffer
 * @date 2020年01月06日 17:36:24
 * @since
 */
@Data
public class ShopifyImage {
    private String id;
    private String productId;
    private int position;
    private String createdAt;
    private String updateAt;
    private String alt;
    private long width;
    private long height;
    private String src;
    private List<String> variantIds = new LinkedList<>();
    private String adminGraphqlApiId;
}
