package com.puffer.shopify.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductImageEntity {
    /**
     * 商品id
     */
    private Integer id;

    /**
     * 商品唯一编码
     */
    private String spu;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 商品id
     *
     * @return id 商品id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 商品id
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
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime create_time 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     *
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     *
     * @param updateTime update_time 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}