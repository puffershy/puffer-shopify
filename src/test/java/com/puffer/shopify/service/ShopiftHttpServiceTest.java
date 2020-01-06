package com.puffer.shopify.service;

import com.puffer.shopify.Application;
import com.puffer.shopify.config.ShopifyProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;

@SpringBootTest(classes = Application.class)
public class ShopiftHttpServiceTest extends AbstractTestNGSpringContextTests {

    @Resource
    private ShopiftHttpService shopiftHttpService;

    @Resource
    private ShopifyProperties shopifyProperties;
    //
    // @Test
    // public void testGetProduct() {
    //     String url = shopifyProperties.getDomainUrl()+"/admin/api/2020-01/products.json";
    //     String product = shopiftHttpService.getProduct(url);
    //     System.out.println("响应参数：" + product);
    // }
}