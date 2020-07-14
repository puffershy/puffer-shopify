package com.puffer.shopify.service.shopify;

import com.puffer.core.log.Log;
import com.puffer.shopify.common.constants.ShopifyConstant;
import com.puffer.shopify.common.constants.ShopifyUrlConstant;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.vo.ProductVO;
import com.puffer.shopify.vo.shopify.ShopifyProduct;
import com.puffer.shopify.vo.shopify.ShopifyProductWrapper;
import com.puffer.shopify.vo.shopify.UploadProductVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * shopify产品服务
 *
 * @author puffer
 * @date 2020年05月18日 17:20:10
 * @since 1.0.0
 */
@Slf4j
@Service
public class ShopifyProductService {
    @Resource
    private ShopiftHttpService shopiftHttpService;

    @Resource
    private ProductDao productDao;

    /**
     * 批量上传产品
     *
     * @param productVOList
     * @return
     * @author puffer
     * @date 2020年07月03日 13:43:07
     * @since 1.0.0
     */
    public void uploadProductList(List<ProductVO> productVOList) {
        productVOList.forEach(productVO -> {
            this.uploadProduct(productVO);
        });
    }

    /**
     * 上传产品
     *
     * @param productVO
     * @return void
     * @author puffer
     * @date 2020年05月23日 17:54:17
     * @since 1.0.0
     */

    public void uploadProduct(ProductVO productVO) {
        UploadProductVO uploadProductVO = buildUploadProductVO(productVO);
        ShopifyProductWrapper result = shopiftHttpService.post(ShopifyUrlConstant.PRODUCT_ADD, uploadProductVO, ShopifyProductWrapper.class);
        if (result == null) {
            return;
        }

        ShopifyProduct product = result.getProduct();
        if (product == null) {
            return;
        }

        int i = productDao.updateProductId(productVO.getSpu(), product.getId(), ProductFlowStateEnum.UPLOAD_SUCCESS.getValue());
        if (i > 0) {
            log.info(Log.newInstance("", "上传产品到shopify成功").kv("spu", productVO.getSpu()).toString());
        }
    }

    /**
     * 构建上传产品请求参数
     *
     * @param productVO
     * @return com.puffer.shopify.vo.shopify.UploadProductVO
     * @author puffer
     * @date 2020年07月03日 13:43:39
     * @since 1.0.0
     */
    private UploadProductVO buildUploadProductVO(ProductVO productVO) {
        ProductDO productDO = productVO.getProductDO();
        List<ProductImageDO> productImageDOList = productVO.getProductImageDOList();

        UploadProductVO.SaveProductDetail uploadProductDetail = new UploadProductVO.SaveProductDetail();
        uploadProductDetail.setTitle(productDO.getNewTitle());
        uploadProductDetail.setBodyHtml(productDO.getNewDescription());
        uploadProductDetail.setVendor(ShopifyConstant.VENDOR);
        uploadProductDetail.setProductType(productDO.getType());
        uploadProductDetail.setTags(productDO.getMaterial());
        //        saveProductDetail.setPublished();
        //        saveProductDetail.setOptions();

        //        saveProductDetail.setMetafields();

        List<UploadProductVO.Image> images = buildImages(productImageDOList, productDO.getNewTitle());

        uploadProductDetail.setImages(images);

        UploadProductVO.Variant variant = new UploadProductVO.Variant();
        variant.setOption1("Default Title");
        variant.setPrice(productDO.getShopifyPrice());
        variant.setCompareAtPrice(variant.getPrice().add(ShopifyConstant.ADD_COMPARE_PRICE));
        variant.setSku(productDO.getSpu());

        uploadProductDetail.setVariants(Lists.newArrayList(variant));

        UploadProductVO uploadProductVO = new UploadProductVO();
        uploadProductVO.setProduct(uploadProductDetail);
        return uploadProductVO;
    }

    /**
     * 构建产品图片
     *
     * @param productImageDOList
     * @param alt
     * @return java.util.List<com.puffer.shopify.vo.shopify.UploadProductVO.Image>
     * @author puffer
     * @date 2020年07月03日 13:44:08
     * @since 1.0.0
     */
    private List<UploadProductVO.Image> buildImages(List<ProductImageDO> productImageDOList, String alt) {
        List<UploadProductVO.Image> list = Lists.newArrayList();

        for (ProductImageDO productImageDO : productImageDOList) {
            UploadProductVO.Image image = new UploadProductVO.Image();
            image.setAlt(alt);
            if (StringUtils.isNotBlank(productImageDO.getImageUrl())) {
                image.setSrc(productImageDO.getImageUrl());
            } else {
                image.setAttachment(productImageDO.getAttachment());
            }

            list.add(image);
        }

        return list;
    }

    /**
     * 查询产品
     *
     * @param productId
     * @return com.puffer.shopify.vo.shopify.ShopifyProduct
     * @author puffer
     * @date 2020年07月14日 23:33:42
     * @since 1.0.0
     */
    public ShopifyProduct queryProduct(String productId) {
        String path = String.format(ShopifyUrlConstant.PRODUCT_SINGLE, productId);
        ShopifyProductWrapper shopifyProductWrapper = shopiftHttpService.get(path, ShopifyProductWrapper.class);

        ShopifyProduct product = shopifyProductWrapper.getProduct();
        return product;
    }

    /**
     * 更新产品信息
     *
     * @param shopifyProduct
     * @return com.puffer.shopify.vo.shopify.ShopifyProduct
     * @author puffer
     * @date 2020年07月14日 23:44:02
     * @since 1.0.0
     */
    public ShopifyProduct updateProduct(ShopifyProduct shopifyProduct) {

        String path = String.format(ShopifyUrlConstant.PRODUCT_SINGLE, shopifyProduct.getId());
        ShopifyProductWrapper shopifyProductWrapper1 = new ShopifyProductWrapper();
        shopifyProductWrapper1.setProduct(shopifyProduct);


        ShopifyProductWrapper shopifyProductWrapper = shopiftHttpService.put(path, shopifyProductWrapper1, ShopifyProductWrapper.class);

        return shopifyProductWrapper.getProduct();
    }

}
