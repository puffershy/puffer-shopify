package com.puffer.shopify.spider.pipeline;

import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.entity.ProductExtDO;
import com.puffer.shopify.service.ProductService;
import com.puffer.shopify.service.ThreadLocalService;
import com.puffer.shopify.vo.ProductExcelVO;
import com.puffer.shopify.vo.ProductVO;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.Map;

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

    @Resource
    private ThreadLocalService threadLocalService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        ProductVO productVO = resultItems.get("productVO");

        Map<String, ProductExcelVO> productExcelVOMap = threadLocalService.queryProductExcel();

        if (productExcelVOMap == null || productExcelVOMap.isEmpty()) {
            productService.saveProductVO(productVO);
            return;
        }

        ProductDO productDO = productVO.getProductDO();
        ProductExcelVO productExcelVO = productExcelVOMap.get(productDO.getUrl());

        ProductExtDO productExtDO = new ProductExtDO();
        productExtDO.setSpu(productDO.getSpu());
        productExtDO.setColor(productExcelVO.getColor() != null ? productExcelVO.getColor().getValue() : "");
        productExtDO.setForType(productExcelVO.getForType() != null ? productExcelVO.getForType().getValue() : "");
        productExtDO.setCapacity(productExcelVO.getCapacity());
        productExtDO.setSafeLevel(productExcelVO.getSafeLevel());
        productExtDO.setQuotes(productExcelVO.getQuotes());

        productVO.setProductExtDO(productExtDO);
        productService.saveProductVO(productVO);
    }

}
