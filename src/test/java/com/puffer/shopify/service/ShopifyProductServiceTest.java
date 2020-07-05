package com.puffer.shopify.service;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.service.shopify.ShopifyProductService;
import com.puffer.shopify.vo.ProductVO;
import com.puffer.shopify.vo.shopify.ShopifyProduct;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;

public class ShopifyProductServiceTest extends AbstractTest {

    @Resource
    private ProductService productService;

    @Resource
    private ShopifyProductService shopifyProductService;


    @Test
    public void testUploadProductList() {

        int size = 1;
        List<ProductVO> productVOList = productService.queryList(ProductFlowStateEnum.TO_UPLOAD,size);

        shopifyProductService.uploadProductList(productVOList);
    }

    @Test
    public void testUploadProduct() {
        String spu = "B08742Y2XN";

        ProductVO productVO = productService.query(spu);

        shopifyProductService.uploadProduct(productVO);
    }

    @Test
    public void testQueryProduct() throws IOException {
        String productId= "46354173133431";
        ShopifyProduct shopifyProduct = shopifyProductService.queryProduct(productId);
        System.out.println(shopifyProduct);
    }
}