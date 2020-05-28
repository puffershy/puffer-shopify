package com.puffer.shopify.mapper;

import com.puffer.shopify.entity.ProductRankDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRankDao {
    int insert(ProductRankDO record);


    /**
     * 批量保存
     *
     * @author puffer
     * @date 2020年05月22日 14:46:30
     * @since 9.3.4
     * @param productRankDOList
     * @return
 * @return int
     */
    int insertList(@Param("list") List<ProductRankDO> productRankDOList);
}