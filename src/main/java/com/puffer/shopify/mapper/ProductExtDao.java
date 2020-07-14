package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.ProductExtDO;
import org.apache.ibatis.annotations.Param;

public interface ProductExtDao {
    int insert(ProductExtDO record);

    ProductExtDO query(@Param("spu") String spu);
}