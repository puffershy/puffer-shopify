package com.puffer.shopify.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
     * 流程状态，0-待上传产品，1-待上传图片，2-待添加集合
     */
    private Byte flowState;

    /**
     * 有效状态，0-无效，1-有效
     */
    private Byte state;

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
     * 亚马逊价格，单位：美元
     *
     * @return amazon_price 亚马逊价格，单位：美元
     */
    public BigDecimal getAmazonPrice() {
        return amazonPrice;
    }

    /**
     * 亚马逊价格，单位：美元
     *
     * @param amazonPrice amazon_price 
     */
    public void setAmazonPrice(BigDecimal amazonPrice) {
        this.amazonPrice = amazonPrice;
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
     * 价格，单位：美元
     *
     * @return shopify_price 价格，单位：美元
     */
    public BigDecimal getShopifyPrice() {
        return shopifyPrice;
    }

    /**
     * 价格，单位：美元
     *
     * @param shopifyPrice shopify_price 
     */
    public void setShopifyPrice(BigDecimal shopifyPrice) {
        this.shopifyPrice = shopifyPrice;
    }

    /**
     * shopify 产品id
     *
     * @return product_id shopify 产品id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * shopify 产品id
     *
     * @param productId product_id 
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * 流程状态，0-待上传产品，1-待上传图片，2-待添加集合
     *
     * @return flow_state 流程状态，0-待上传产品，1-待上传图片，2-待添加集合
     */
    public Byte getFlowState() {
        return flowState;
    }

    /**
     * 流程状态，0-待上传产品，1-待上传图片，2-待添加集合
     *
     * @param flowState flow_state 
     */
    public void setFlowState(Byte flowState) {
        this.flowState = flowState;
    }

    /**
     * 有效状态，0-无效，1-有效
     *
     * @return state 有效状态，0-无效，1-有效
     */
    public Byte getState() {
        return state;
    }

    /**
     * 有效状态，0-无效，1-有效
     *
     * @param state state 
     */
    public void setState(Byte state) {
        this.state = state;
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