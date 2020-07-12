package com.puffer.shopify.spider.pipeline;

import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.service.ThreadLocalService;
import com.puffer.shopify.vo.ProductExcelVO;
import com.puffer.shopify.vo.ProductVO;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * amazon 保存excel
 *
 * @author puffer
 * @date 2020年07月11日 09:26:27
 * @since 1.0.0
 */

@Component
public class AmazonExcelPipeline implements Pipeline {
    @Resource
    private ThreadLocalService threadLocalService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        ProductVO productVO = resultItems.get("productVO");
        ProductDO productDO = productVO.getProductDO();
        List<ProductImageDO> productImageDOList = productVO.getProductImageDOList();

        Map<String, ProductExcelVO> productExcelVOMap = threadLocalService.queryProductExcel();
        ProductExcelVO productExcelVO = productExcelVOMap.get(productDO.getUrl());
//        productExcelVO.setUrl();
//        productExcelVO.setSource();
//        productExcelVO.setColor();
//        productExcelVO.setForType();
        productExcelVO.setSpu(productDO.getSpu());
        productExcelVO.setTitle(productDO.getTitle());
        productExcelVO.setDescription(productDO.getDescription());
        productExcelVO.setPrice(productDO.getAmazonPrice());
//        productExcelVO.setKeywords();
//        productExcelVO.setNewTitle();
//        productExcelVO.setNewDescription();
//        productExcelVO.setNewPrice();
        productExcelVO.setImageUrl(productImageDOList.get(0).getImageUrl());


    }
}