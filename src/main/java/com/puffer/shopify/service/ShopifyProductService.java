package com.puffer.shopify.service;

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
import java.math.BigDecimal;
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

    public void uploadProductList(List<ProductVO> productVOList){
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
        if(i > 0){
            log.info(Log.newInstance("","上传产品到shopify成功").kv("spu",productVO.getSpu()).toString());
        }
    }

    private UploadProductVO buildUploadProductVO(ProductVO productVO) {
        ProductDO productDO = productVO.getProductDO();
        List<ProductImageDO> productImageDOList = productVO.getProductImageDOList();

        UploadProductVO.SaveProductDetail uploadProductDetail = new UploadProductVO.SaveProductDetail();
        uploadProductDetail.setTitle(productDO.getTitle());
        uploadProductDetail.setBodyHtml(productDO.getDescription());
        uploadProductDetail.setVendor(ShopifyConstant.VENDOR);
        uploadProductDetail.setProductType(productDO.getType());
        uploadProductDetail.setTags(productDO.getMaterial());
//        saveProductDetail.setPublished();
//        saveProductDetail.setOptions();

//        saveProductDetail.setMetafields();


        ProductImageDO productImageDO = productImageDOList.get(0);
        UploadProductVO.Image image = new UploadProductVO.Image();
        if (StringUtils.isNotBlank(productImageDO.getImageUrl())) {
            image.setSrc(productImageDO.getImageUrl());
        } else {
            image.setAttachment(productImageDO.getAttachment());
        }

        uploadProductDetail.setImages(Lists.newArrayList(image));


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


}
