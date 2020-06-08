package com.puffer.shopify.common.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author puffer
 * @date 2020年06月08日 19:26:01
 * @since
 */
@Data
@XStreamAlias("item")
public class GoogleItem {

    @XStreamAlias("title")
    private String title;
    @XStreamAlias("link")
    private String link;
    @XStreamAlias("description")
    private String description;
    @XStreamAlias("g:google_product_category")
    private String googleProductCategory;

    @XStreamAlias("g:item_group_id")
    private String item_group_id;

    @XStreamAlias("g:id")
    private String id;

    @XStreamAlias("g:condition")
    private String condition;

    @XStreamAlias("g:price")
    private String price;
    @XStreamAlias("g:availability")
    private String availability;
    @XStreamAlias("g:image_link")
    private String imageLink;
    @XStreamAlias("g:brand")
    private String brand;
    @XStreamAlias("g:mpn")
    private String mpn;
    @XStreamAlias("g:product_type")
    private String productType;
    @XStreamAlias("g:age_group")
    private String ageGroup;
    @XStreamAlias("g:gender")
    private String gender;
    @XStreamAlias("g:custom_label_0")
    private String customLabel0;
    @XStreamAlias("g:custom_label_1")
    private String customLabel1;
    @XStreamAlias("g:custom_label_2")
    private String customLabel2;
    @XStreamAlias("g:custom_label_3")
    private String customLabel3;
    @XStreamAlias("g:custom_label_4")
    private String customLabel4;
    @XStreamAlias("g:shipping_weight")
    private String shippingWeight;
    @XStreamAlias("g:pattern")
    private String pattern;
    @XStreamAlias("g: color")
    private String color;
}
