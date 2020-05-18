package com.puffer.shopify.service;
import com.puffer.shopify.vo.shopify.SaveProductVO;

import com.puffer.shopify.entity.AmazonItemEntity;
import org.springframework.stereotype.Service;

/**
 * shopify产品服务
 *
 * @author buyi
 * @date 2020年05月18日 17:20:10
 * @since 1.0.0
 */
@Service
public class ShopifyProductService {

    public void saveProduct(AmazonItemEntity amazonItem){
        SaveProductVO shopifyProduct = buildSaveProductVO(amazonItem);

    }

    private SaveProductVO buildSaveProductVO(AmazonItemEntity amazonItem) {

        return null;
    }

}
