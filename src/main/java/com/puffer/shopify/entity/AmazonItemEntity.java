package com.puffer.shopify.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AmazonItemEntity {
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
     * 商品标题
     */
    private String title;

    /**
     * 价格，单位：美元
     */
    private BigDecimal origPrice;

    /**
     * 商品URL
     */
    private String url;

    /**
     * 最新排名
     */
    private Integer rank;

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
     * 商品类别
     *
     * @return type 商品类别
     */
    public String getType() {
        return type;
    }

    /**
     * 商品类别
     *
     * @param type type 
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 商品标题
     *
     * @return title 商品标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 商品标题
     *
     * @param title title 
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 价格，单位：美元
     *
     * @return orig_price 价格，单位：美元
     */
    public BigDecimal getOrigPrice() {
        return origPrice;
    }

    /**
     * 价格，单位：美元
     *
     * @param origPrice orig_price 
     */
    public void setOrigPrice(BigDecimal origPrice) {
        this.origPrice = origPrice;
    }

    /**
     * 商品URL
     *
     * @return url 商品URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 商品URL
     *
     * @param url url 
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 最新排名
     *
     * @return rank 最新排名
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * 最新排名
     *
     * @param rank rank 
     */
    public void setRank(Integer rank) {
        this.rank = rank;
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

    /**
     * 描述
     *
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     *
     * @param description description 
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}