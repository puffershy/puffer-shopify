package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int insert(ProductEntity record);

    List<ProductEntity> queryList(@Param("state") int state, @Param("index") int index, @Param("size") int size);

    ProductEntity queryByProductId(@Param("productId") String productId);

    int updateProductId(@Param("spu") String spu, @Param("productId") String productId);

    int updateState(@Param("spu") String spu, @Param("state") int state);
}