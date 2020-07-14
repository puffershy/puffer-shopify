package com.puffer.shopify.entity;


import lombok.Data;

@Data
public class KeywordDO {
    private Long id;

    private String keyword;

    private Integer searches;
}