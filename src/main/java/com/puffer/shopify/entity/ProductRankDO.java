package com.puffer.shopify.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductRankDO {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 商品唯一编码
     */
    private String spu;

    /**
     * 排名类型
     */
    private String rankType;

    /**
     * 排名
     */
    private Integer rank;

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
     * 排名类型
     *
     * @return rank_type 排名类型
     */
    public String getRankType() {
        return rankType;
    }

    /**
     * 排名类型
     *
     * @param rankType rank_type 
     */
    public void setRankType(String rankType) {
        this.rankType = rankType == null ? null : rankType.trim();
    }

    /**
     * 排名
     *
     * @return rank 排名
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * 排名
     *
     * @param rank rank 
     */
    public void setRank(Integer rank) {
        this.rank = rank;
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