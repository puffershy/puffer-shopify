package com.puffer.shopify.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductEntity {
    /**
     * 商品唯一编码
     */
    private String spu;

    /**
     * shopify 产品id
     */
    private String productId;

    /**
     * 价格，单位：美元
     */
    private BigDecimal price;

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
     * 价格，单位：美元
     *
     * @return price 价格，单位：美元
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 价格，单位：美元
     *
     * @param price price 
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}