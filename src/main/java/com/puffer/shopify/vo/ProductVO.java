package com.puffer.shopify.vo;

import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.entity.ProductRankDO;
import com.puffer.shopify.entity.ProductVariantDO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 亚马逊产品快照类
 *
 * @author puffer
 * @date 2020年05月21日 19:00:22
 * @since 1.0.0
 */
@Builder
@Data
public class ProductVO {

    /**
     * 产品信息
     */
    private ProductDO productDO;

    /**
     * 产品排名信息
     */
    private List<ProductRankDO> productRankDOList;

    /**
     * 变体列表
     */
    private List<ProductVariantDO> variantDOList;

    /**
     * 产品图片信息
     */
    private List<ProductImageDO> productImageDOList;


    public String getSpu(){
        return productDO.getSpu();
    }

}
