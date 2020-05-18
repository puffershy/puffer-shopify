package com.puffer.shopify.mapper;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.entity.ProductEntity;
import org.testng.annotations.Test;

import javax.annotation.Resource;

public class ProductMapperTest extends AbstractTest {

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testQueryByProductId() {
        ProductEntity productEntity = productMapper.queryByProductId("123");
        System.out.println(productEntity);
    }
}