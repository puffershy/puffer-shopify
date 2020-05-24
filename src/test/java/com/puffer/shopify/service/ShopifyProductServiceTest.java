package com.puffer.shopify.service;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.vo.ProductVO;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.testng.Assert.*;

public class ShopifyProductServiceTest extends AbstractTest {

    @Resource
    private ProductService productService;

    @Resource
    private ShopifyProductService shopifyProductService;


    @Test
    public void testUploadProductList() {

        int size = 150;
        List<ProductVO> productVOList = productService.queryList(ProductFlowStateEnum.TO_UPLOAD,size);

        shopifyProductService.uploadProductList(productVOList);
    }

    @Test
    public void testUploadProduct() {
        String spu = "B08742Y2XN";

        ProductVO productVO = productService.query(spu);

        shopifyProductService.uploadProduct(productVO);
    }
}