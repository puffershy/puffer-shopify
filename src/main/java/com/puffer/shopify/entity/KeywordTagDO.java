package com.puffer.shopify.entity;

import java.util.Date;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class KeywordTagDO {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 关键词Id
     */
    private Long keywordId;

    /**
     * 标签号
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

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