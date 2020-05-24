package com.puffer.shopify.service;

import com.google.common.collect.Lists;
import com.puffer.core.log.Log;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.common.enums.ProductStateEnum;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.entity.ProductRankDO;
import com.puffer.shopify.entity.ProductVariantDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.mapper.ProductImageDao;
import com.puffer.shopify.mapper.ProductRankDao;
import com.puffer.shopify.mapper.ProductVariantDao;
import com.puffer.shopify.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品服务
 *
 * @author buyi
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

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveProductVO(ProductVO productVO) {
        //step1.保存产品信息
        try {
            productDao.insert(productVO.getProductDO());
        } catch (DuplicateKeyException e) {
            log.info(Log.newInstance("", "产品已经存在").kv("spu", productVO.getProductDO().getSpu()).kv("url", productVO.getProductDO().getUrl()).toString());
            return;
        }

        try {

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
            List<ProductVariantDO> variantDOList = productVO.getVariantDOList();
            if (CollectionUtils.isNotEmpty(variantDOList)) {
                productVariantDao.insertList(variantDOList);
            }
        } catch (Exception e) {
            log.error(Log.newInstance("","保存数据异常").kv("spu", productVO.getProductDO().getSpu()).kv("url", productVO.getProductDO().getUrl()).toString(),e);
            throw  e;
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
}
