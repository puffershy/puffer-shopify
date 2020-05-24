package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.ProductImageDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImageDao {
    int insert(ProductImageDO record);

    /**
     * 批量保存
     *
     * @param productImageDOList
     * @return int
     * @author buyi
     * @date 2020年05月22日 14:47:55
     * @since 9.3.4
     */
    int insertList(@Param("list") List<ProductImageDO> productImageDOList);

    List<ProductImageDO> queryList(@Param("spu") String spu);

    int updateUrl(@Param("spu") String spu, @Param("imageUrl") String imageUrl);
}