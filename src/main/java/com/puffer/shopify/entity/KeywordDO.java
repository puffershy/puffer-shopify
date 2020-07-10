package com.puffer.shopify.entity;


import lombok.Data;

@Data
public class KeywordDO {
    private Integer id;

    private String keyword;

    private Integer searches;

    private String type;
}