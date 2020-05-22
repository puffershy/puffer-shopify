package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.ProductDO;

public interface ProductDao {
    /**
     * 保存产品信息
     *
     * @param record
     * @return int
     * @author buyi
     * @date 2020年05月22日 14:41:45
     * @since 9.3.4
     */
    int insert(ProductDO record);
}