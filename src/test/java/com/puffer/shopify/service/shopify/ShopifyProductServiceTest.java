package com.puffer.shopify.service.shopify;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.service.ProductService;
import com.puffer.shopify.vo.ProductVO;
import com.puffer.shopify.vo.shopify.ShopifyImage;
import com.puffer.shopify.vo.shopify.ShopifyProduct;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class ShopifyProductServiceTest extends AbstractTest {

    @Resource
    private ProductService productService;

    @Resource
    private ShopifyProductService shopifyProductService;

    @Resource
    private ProductDao productDao;


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

    @Test
    public void testUpdateProduct() {
        String productId= "4635413053503";

        ShopifyProduct shopifyProduct1= shopifyProductService.queryProduct(productId);

        ShopifyProduct shopifyProduct = new ShopifyProduct();
        shopifyProduct.setId(productId);
        List<ShopifyImage> images = Lists.newArrayList();

        for (ShopifyImage shopifyImage : shopifyProduct1.getImages()) {
            ShopifyImage image = new ShopifyImage();
            image.setId(shopifyImage.getId());
            image.setAlt(shopifyProduct1.getTitle());

            images.add(image);
        }

        shopifyProduct.setImages(images);

//        shopifyProductService.updateProduct(shopifyProduct);



    }
}