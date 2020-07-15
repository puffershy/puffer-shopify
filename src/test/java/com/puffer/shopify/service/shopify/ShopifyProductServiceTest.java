package com.puffer.shopify.service.shopify;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.common.enums.ProductStateEnum;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.service.ProductService;
import com.puffer.shopify.vo.ProductVO;
import com.puffer.shopify.vo.shopify.ShopifyImage;
import com.puffer.shopify.vo.shopify.ShopifyMetafield;
import com.puffer.shopify.vo.shopify.ShopifyProduct;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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
        List<ProductVO> productVOList = productService.queryList(ProductFlowStateEnum.TO_UPLOAD, size);

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
        String productId = "4635413053503";
        ShopifyProduct shopifyProduct = shopifyProductService.queryProduct(productId);
        System.out.println(shopifyProduct);
    }

    /**
     * 更新图片alt
     *
     * @param
     * @return
     * @author puffer
     * @date 2020年07月15日 09:51:00
     * @since 1.0.0
     */
    @Test
    public void testUpdateProductImageAlt() {
        // String productId= "4635413053503";
        int size = 300;

        List<ProductDO> productDOS = productDao.queryList(ProductFlowStateEnum.UPLOAD_SUCCESS.getValue(), size, ProductStateEnum.EFFECTIVE.getValue());

        for (ProductDO productDO : productDOS) {
            String productId = productDO.getProductId();
            ShopifyProduct shopifyProduct1 = shopifyProductService.queryProduct(productId);
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

            shopifyProductService.updateProduct(shopifyProduct);

            productDao.updateFlowState(productDO.getSpu(), ProductFlowStateEnum.UPDATE_FROM_SHOPIFY.getValue());
        }

    }

    @Test
    public void testQueryProductMetafields() {

        String productId = "4635413053503";
        List<ShopifyMetafield> shopifyMetafields = shopifyProductService.queryProductMetafields(productId);
        System.out.println(shopifyMetafields);
    }
}