package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.ProductDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    int updateProductId(@Param("spu") String spu, @Param("productId") String productId,@Param("flowState") int flowState);

    List<ProductDO> queryList(@Param("flowState") int flowState, @Param("size") int size, @Param("state") int state);


    ProductDO query(@Param("spu")String spu);
}