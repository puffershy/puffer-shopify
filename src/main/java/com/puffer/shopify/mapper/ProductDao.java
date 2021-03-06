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
     * @author puffer
     * @date 2020年05月22日 14:41:45
     * @since 9.3.4
     */
    int insert(ProductDO record);

    int updateProductId(@Param("spu") String spu, @Param("productId") String productId, @Param("flowState") int flowState);

    List<ProductDO> queryList(@Param("flowState") int flowState, @Param("size") int size, @Param("state") int state);

    ProductDO query(@Param("spu") String spu);

    List<ProductDO> queryAll();

    /**
     * 更新产品信息
     *
     * @param productDO
     * @return
     * @author puffer
     * @date 2020年07月03日 14:25:42
     * @since 1.0.0
     */
    int updateTitle(@Param("po") ProductDO productDO);

    /**
     * 更新流程状态
     *
     * @param spu
     * @param flowState
     * @return int
     * @author puffer
     * @date 2020年07月15日 09:36:25
     * @since 1.0.0
     */
    int updateFlowState(@Param("spu") String spu, @Param("flowState") int flowState);
}