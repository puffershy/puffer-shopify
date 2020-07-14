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
    private String quotes;
    private Integer capacity;
    private Integer safeLevel;
    private BigDecimal price;

    private String spu;
    private String title;
    private String description;
    private String keywords;
    private String newTitle;
    private String newDescription;
    private BigDecimal newPrice;
    private String imageUrl;

    public static String[] createTitle() {
        String[] title = new String[16];
        title[0] = "url";
        title[1] = "source";
        title[2] = "color";
        title[3] = "for";
        title[4] = "quotes";
        title[5] = "capacity";
        title[6] = "safeLevel";
        title[7] = "price";
        title[8] = "spu";
        title[9] = "title";
        title[10] = "description";
        title[11] = "keywords";
        title[12] = "newTitle";
        title[13] = "newDescription";
        title[14] = "newPrice";
        title[15] = "imageUrl";
        return title;
    }

    public String[] transfer() {
        String[] title = new String[16];
        title[0] = getUrl();
        title[1] = getSource();
        title[2] = getColor() != null ? getColor().getValue() : "";
        title[3] = getForType() != null ? getForType().getValue() : "";
        title[4] = getQuotes();
        title[5] = getCapacity().toString();
        title[6] = getSafeLevel().toString();
        title[7] = getPrice().toString();
        title[8] = getSpu();
        title[9] = getTitle();
        title[10] = getDescription();
        title[11] = getKeywords();
        title[12] = getNewTitle();
        title[13] = getNewDescription();
        title[14] = getNewPrice().toString();
        title[15] = getImageUrl();

        return title;
    }

}