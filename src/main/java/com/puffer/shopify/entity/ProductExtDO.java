package com.puffer.shopify.entity;

import java.util.Date;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class ProductExtDO {
    /**
     * 商品id
     */
    private Integer id;

    /**
     * 商品唯一编码
     */
    private String spu;

    /**
     * 颜色
     */
    private String color;

    /**
     * 适应人群，如果全适应，则空
     */
    private String forType;

    /**
     * 容量，单位oz
     */
    private Integer capacity;

    /**
     * 安全等级，1-微波炉洗挖机安全，2-微波炉安全，3-都不安全，4-洗碗机安全
     */
    private Integer safeLevel;

    /**
     * 引用
     */
    private String quotes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}