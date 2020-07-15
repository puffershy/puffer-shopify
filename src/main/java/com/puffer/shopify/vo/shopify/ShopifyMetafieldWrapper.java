package com.puffer.shopify.vo.shopify;

import lombok.Data;

import java.util.List;

@Data
public class ShopifyMetafieldWrapper {
    private List<ShopifyMetafield> metafields;
}