package com.puffer.shopify.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Data
public class ProductDO {
    /**
     * 商品id
     */
    private Integer id;

    /**
     * 商品唯一编码
     */
    private String spu;

    /**
     * 商品类别
     */
    private String type;

    /**
     * 材质
     */
    private String material;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 亚马逊价格，单位：美元
     */
    private BigDecimal amazonPrice;

    /**
     * 商品URL
     */
    private String url;

    /**
     * 价格，单位：美元
     */
    private BigDecimal shopifyPrice;

    /**
     * shopify 产品id
     */
    private String productId;

    /**
     * 是否免物流
     */
    private Integer freeShipping;

    /**
     * 流程状态，0-待上传产品，1-待上传图片，2-待添加集合
     */
    private Integer flowState;

    /**
     * 有效状态，0-无效，1-有效
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 描述
     */
    private String description;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}