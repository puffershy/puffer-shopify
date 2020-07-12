package com.puffer.shopify.vo;

import com.puffer.shopify.common.enums.ColorEnum;
import com.puffer.shopify.common.enums.ForTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductExcelVO {
    private String url;
    private String source;
    private ColorEnum color;
    private ForTypeEnum forType;

    private String spu;
    private String title;
    private String description;
    private BigDecimal price;
    private String keywords;
    private String newTitle;
    private String newDescription;
    private BigDecimal newPrice;
    private String imageUrl;

}