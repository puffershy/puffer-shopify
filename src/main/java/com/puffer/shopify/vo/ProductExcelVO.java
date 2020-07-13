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

    public static String[] createTitle() {
        String[] title = new String[13];
        title[0] = "spu";
        title[1] = "url";
        title[2] = "source";
        title[3] = "color";
        title[4] = "for";
        title[5] = "quotes";
        title[6] = "title";
        title[7] = "description";
        title[8] = "price";
        title[9] = "keywords";
        title[10] = "newTitle";
        title[11] = "newDescription";
        title[12] = "newPrice";
        title[13] = "imageUrl";
        return title;
    }

    public String[] transfer() {
        String[] title = new String[13];
        title[0] = getSpu();
        title[1] = getUrl();
        title[2] = getSource();
        title[3] = getColor().getValue();
        title[4] = getForType().getValue();
        title[5] = "";
        title[6] = getTitle();
        title[7] = getDescription();
        title[8] = getPrice().toString();
        title[9] = "";
        title[10] = "";
        title[11] = "";
        title[12] = "";
        title[13] = getImageUrl();
        return title;
    }

}