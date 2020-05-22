package com.puffer.shopify.service;

import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.entity.ProductRankDO;
import com.puffer.shopify.entity.ProductVariantDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.mapper.ProductImageDao;
import com.puffer.shopify.mapper.ProductRankDao;
import com.puffer.shopify.mapper.ProductVariantDao;
import com.puffer.shopify.vo.ProductVO;
import org.apache.commons.collections.CollectionUtils;
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
        productDao.insert(productVO.getProductDO());

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
    }
}
