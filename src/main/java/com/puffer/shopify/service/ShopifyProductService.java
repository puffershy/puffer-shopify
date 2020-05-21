package com.puffer.shopify.service;

import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.vo.shopify.SaveProductVO;
import org.springframework.stereotype.Service;

/**
 * shopify产品服务
 *
 * @author puffer
 * @date 2020年05月18日 17:20:10
 * @since 1.0.0
 */
@Service
public class ShopifyProductService {

    public void saveProduct(ProductDO productDO) {
        SaveProductVO shopifyProduct = buildSaveProductVO(productDO);

    }

    private SaveProductVO buildSaveProductVO(ProductDO productDO) {

        return null;
    }

}
