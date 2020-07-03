package com.puffer.shopify.service;

import com.google.common.collect.Lists;
import com.puffer.core.log.Log;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.common.enums.ProductStateEnum;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.entity.ProductRankDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.mapper.ProductImageDao;
import com.puffer.shopify.mapper.ProductRankDao;
import com.puffer.shopify.mapper.ProductVariantDao;
import com.puffer.shopify.service.shopify.ShopifyProductService;
import com.puffer.shopify.vo.ProductVO;
import com.puffer.shopify.vo.shopify.ShopifyProduct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品服务
 *
 * @author puffer
 * @date 2020年05月22日 15:06:30
 * @since 1.0.0
 */
@Slf4j
@Service
public class ProductService {
    @Resource
    private ProductDao productDao;

    @Resource
    private ProductRankDao productRankDao;

    @Resource
    private ProductImageDao productImageDao;

    @Resource
    private ProductVariantDao productVariantDao;

    @Resource
    private ShopifyProductService shopifyProductService;

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveProductVO(ProductVO productVO) {
        try {
            //step1.保存产品信息
            try {
                productDao.insert(productVO.getProductDO());
            } catch (DuplicateKeyException e) {
                log.info(Log.newInstance("", "产品已经存在").kv("spu", productVO.getProductDO().getSpu()).kv("url", productVO.getProductDO().getUrl()).toString());
                return;
            }

            //step2. 保存产品排行榜
            List<ProductRankDO> productRankDOList = productVO.getProductRankDOList();
            if (CollectionUtils.isNotEmpty(productRankDOList)) {
                productRankDao.insertList(productRankDOList);
            }

            //step3. 保存产品图片
            List<ProductImageDO> productImageDOList = productVO.getProductImageDOList();
            if (CollectionUtils.isNotEmpty(productImageDOList)) {
                productImageDao.insertList(productImageDOList);
            }

            //step4. 保存变体
            //            List<ProductVariantDO> variantDOList = productVO.getVariantDOList();
            //            if (CollectionUtils.isNotEmpty(variantDOList)) {
            //                productVariantDao.insertList(variantDOList);
            //            }
        } catch (Exception e) {
            log.error(Log.newInstance("", "保存数据异常").kv("spu", productVO.getProductDO().getSpu()).kv("url", productVO.getProductDO().getUrl()).toString(), e);
            // throw e;
        }
    }

    public List<ProductVO> queryList(ProductFlowStateEnum flowStateEnum, int size) {

        List<ProductVO> list = Lists.newArrayListWithCapacity(size);

        List<ProductDO> productDOS = productDao.queryList(flowStateEnum.getValue(), size, ProductStateEnum.EFFECTIVE.getValue());

        for (ProductDO productDO : productDOS) {

            ProductVO productVO = buildProductVO(productDO);
            if (productVO == null) {
                continue;
            }

            list.add(productVO);

        }

        return list;

    }

    private ProductVO buildProductVO(ProductDO productDO) {
        List<ProductImageDO> productImageDOS = productImageDao.queryList(productDO.getSpu());
        if (CollectionUtils.isEmpty(productImageDOS)) {
            return null;
        }

        return ProductVO.builder().productDO(productDO).productImageDOList(productImageDOS).build();
    }

    public ProductVO query(String spu) {
        ProductDO productDO = productDao.query(spu);
        return buildProductVO(productDO);
    }

    /**
     * 更新产品信息
     *
     * @param productVO
     * @return
     * @author puffer
     * @date 2020年07月03日 14:19:17
     * @since 1.0.0
     */
    public void updateProductVO(ProductVO productVO) {
        //更新图片url
        List<ProductImageDO> productImageDOList = productVO.getProductImageDOList();
        for (ProductImageDO productImageDO : productImageDOList) {
            if (StringUtils.isNotBlank(productImageDO.getImageUrl())) {
                int i = productImageDao.updateUrl(productImageDO.getSpu(), productImageDO.getImageUrl());
                if (i > 0) {
                    log.info(Log.newInstance("", "更新图片信息完成").kv("spu", productVO.getSpu()).toString());
                }
            }
        }
    }

    /**
     * 从shopify更新产品信息到本地
     *
     * @param productDO
     * @author puffer
     * @date 2020年07月03日 14:20:50
     * @since 1.0.0
     */
    public void updateFromShopify(ProductDO productDO) {

        //step1. 查询shopify产品信息
        ShopifyProduct shopifyProduct = shopifyProductService.queryProduct(productDO.getProductId());

        if (shopifyProduct == null) {
            productDO.setState(ProductStateEnum.DELETE.getValue());
        } else {
            productDO.setTitle(shopifyProduct.getTitle());
            productDO.setDescription(shopifyProduct.getBodyHtml());
            productDO.setState(ProductStateEnum.EFFECTIVE.getValue());
        }

        productDao.updateTitle(productDO);

    }
}
