package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int insert(ProductEntity record);

    ProductEntity queryByProductId(@Param("productId") String productId);
}