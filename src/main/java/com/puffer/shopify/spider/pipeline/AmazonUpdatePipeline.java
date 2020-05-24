package com.puffer.shopify.spider.pipeline;

import com.puffer.shopify.service.ProductService;
import com.puffer.shopify.vo.ProductVO;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * 亚马逊商品信息持久化
 *
 * @author puffer
 * @date 2019年10月14日 22:47:47
 * @since 1.0.0
 */
@Component
public class AmazonUpdatePipeline implements Pipeline {

    @Resource
    private ProductService productService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        ProductVO productVO = resultItems.get("productVO");
        productService.updateProductVO(productVO);
    }

}
