package com.puffer.shopify.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author puffer
 * @date 2020年01月06日 17:36:24
 * @since
 */
@Data
public class ShopifyVariant {

    private String id;

    private String productId;
    private String title;
    private BigDecimal price;
    private String sku;
    private int position;
    private String inventoryPolicy;
    private BigDecimal compareAtPrice;
    private String fulfillmentService;
    private String inventoryManagement;
    private String option1;
    private String option2;
    private String option3;
    private String createdAt;
    private String updateAt;
    private boolean taxable;
    private String barcode;
    private long grams;
    private String imageId;
    private float weight;
    private String weightUnit;
    private String inventoryItemId;
    private Long inventoryQuantity;
    private Long oldInventoryQuantity;
    private boolean requiresShipping;
    private String adminGraphqlApiId;
}
