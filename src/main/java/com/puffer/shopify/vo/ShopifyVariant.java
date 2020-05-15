package com.puffer.shopify.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 变体
 *
 * @author puffer
 * @date 2020年01月06日 17:36:24
 * @since
 */
@Data
public class ShopifyVariant {

    private String id;

    @JSONField(name = "product_id")
    private String productId;
    private String title;
    private BigDecimal price;
    private String sku;
    private int position;

    @JSONField(name = "inventory_policy")
    private String inventoryPolicy;

    @JSONField(name = "compare_at_price")
    private BigDecimal compareAtPrice;

    @JSONField(name = "fulfillment_service")
    private String fulfillmentService;

    @JSONField(name = "inventory_management")
    private String inventoryManagement;
    private String option1;
    private String option2;
    private String option3;

    @JSONField(name = "created_at")
    private String createdAt;

    @JSONField(name = "updated_at")
    private String updateAt;
    private boolean taxable;
    private String barcode;
    private long grams;

    @JSONField(name = "image_id")
    private String imageId;
    private float weight;

    @JSONField(name = "weight_unit")
    private String weightUnit;

    @JSONField(name = "inventory_item_id")
    private String inventoryItemId;

    @JSONField(name = "inventory_quantity")
    private Long inventoryQuantity;

    @JSONField(name = "old_inventory_quantity")
    private Long oldInventoryQuantity;

    @JSONField(name = "requires_shipping")
    private boolean requiresShipping;

    @JSONField(name = "admin_graphql_api_id")
    private String adminGraphqlApiId;

    @JSONField(name = "presentment_prices")
    private List<PresentmentPrice> presentmentPrices;

    @Data
    public static class PresentmentPrice {
        private Price price;
        @JSONField(name = "compare_at_price")
        private BigDecimal compareAtPrice;

        @Data
        public static class Price {
            @JSONField(name = "currency_code")
            private String currencyCode;
            private BigDecimal amount;
        }
    }
}
