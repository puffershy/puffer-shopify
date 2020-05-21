package com.puffer.shopify.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductVariantDO {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 商品唯一编码
     */
    private String spu;

    /**
     * 关键词
     */
    private String key;

    /**
     * 值
     */
    private Integer value;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private Date create;

    /**
     * 自增主键
     *
     * @return id 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增主键
     *
     * @param id id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 商品唯一编码
     *
     * @return spu 商品唯一编码
     */
    public String getSpu() {
        return spu;
    }

    /**
     * 商品唯一编码
     *
     * @param spu spu 
     */
    public void setSpu(String spu) {
        this.spu = spu == null ? null : spu.trim();
    }

    /**
     * 关键词
     *
     * @return key 关键词
     */
    public String getKey() {
        return key;
    }

    /**
     * 关键词
     *
     * @param key key 
     */
    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    /**
     * 值
     *
     * @return value 值
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 值
     *
     * @param value value 
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 图片地址
     *
     * @return image_url 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 图片地址
     *
     * @param imageUrl image_url 
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * 创建时间
     *
     * @return create 创建时间
     */
    public Date getCreate() {
        return create;
    }

    /**
     * 创建时间
     *
     * @param create create 
     */
    public void setCreate(Date create) {
        this.create = create;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}