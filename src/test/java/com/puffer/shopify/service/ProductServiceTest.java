package com.puffer.shopify.service;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.common.enums.ProductStateEnum;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.mapper.ProductDao;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class ProductServiceTest extends AbstractTest {
    @Resource
    private ProductService productService;
    @Resource
    private ProductDao productDao;



    @Test
    public void testUpdateFromShopify() {
        int size = 300;

        List<ProductDO> productDOS = productDao.queryList(ProductFlowStateEnum.UPLOAD_SUCCESS.getValue(), size, ProductStateEnum.EFFECTIVE.getValue());
        for (ProductDO productDO : productDOS) {
            productService.updateFromShopify(productDO);
        }
    }

    @Test
    public void testCollectProduct() throws IOException, InterruptedException {
        String filePath = "E:\\workspace\\workspace-puffer\\github\\puffer-shopify\\doc\\product_url_20200714.xlsx";
        productService.collectProduct(filePath);

        Thread.sleep(600000);
    }

    @Test
    public void testCreateExcel() throws IOException {
        String filePath = "E:\\workspace\\workspace-puffer\\github\\puffer-shopify\\doc\\product_url_20200714.xlsx";
        productService.createExcel(ProductFlowStateEnum.TO_UPDATE_TITLE, filePath);
    }
}