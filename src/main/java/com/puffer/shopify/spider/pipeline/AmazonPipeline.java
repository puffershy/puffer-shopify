package com.puffer.shopify.spider.pipeline;

import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.entity.ProductRankDO;
import com.puffer.shopify.entity.ProductVariantDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.mapper.ProductImageDao;
import com.puffer.shopify.mapper.ProductRankDao;
import com.puffer.shopify.mapper.ProductVariantDao;
import com.puffer.shopify.service.ProductService;
import com.puffer.shopify.vo.ProductVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.List;

/**
 * 亚马逊商品信息持久化
 *
 * @author puffer
 * @date 2019年10月14日 22:47:47
 * @since 1.0.0
 */
@Component
public class AmazonPipeline implements Pipeline {

    @Resource
    private ProductService productService;

    // @Resource
    // private ProductDao productDao;
    //
    // @Resource
    // private ProductRankDao productRankDao;
    //
    // @Resource
    // private ProductImageDao productImageDao;
    //
    // @Resource
    // private ProductVariantDao productVariantDao;

    // @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void process(ResultItems resultItems, Task task) {
        ProductVO productVO = resultItems.get("productVO");
        productService.saveProductVO(productVO);
    }

}
