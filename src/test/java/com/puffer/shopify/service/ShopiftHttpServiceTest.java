package com.puffer.shopify.service;

import com.puffer.shopify.Application;
import com.puffer.shopify.common.constants.ShopifyUrlConstant;
import com.puffer.shopify.config.ShopifyProperties;
import com.puffer.shopify.vo.shopify.ShopifyProduct;
import com.puffer.shopify.vo.shopify.ShopifyProductWrapper;
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

    /**
     * 添加产品
     *
     * @param
     * @return void
     * @author puffer
     * @date 2020年05月17日 22:36:17
     * @since 1.0.0
     */

    @Test
    public void testAddProduct() {
        ShopifyProduct shopifyProduct = new ShopifyProduct();
        shopifyProduct.setTitle("auto create product"+System.currentTimeMillis());
        shopifyProduct.setBodyHtml("product description");
        shopifyProduct.setVendor("Burton");
        shopifyProduct.setProductType("auto test");

        ShopifyProductWrapper shopifyProductWrapper = new ShopifyProductWrapper();
        shopifyProductWrapper.setProduct(shopifyProduct);

        ShopifyProductWrapper post = shopiftHttpService.post(ShopifyUrlConstant.PRODUCT_ADD, shopifyProductWrapper, ShopifyProductWrapper.class);

        System.out.println(post);

    }
}