package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.ProductVariantDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductVariantDao {
    int insert(ProductVariantDO record);

    /**
     * 批量保存
     *
     * @param variantDOList
     * @return int
     * @author buyi
     * @date 2020年05月22日 14:50:14
     * @since 9.3.4
     */
    int insertList(@Param("list") List<ProductVariantDO> variantDOList);
}