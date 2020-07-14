package com.puffer.shopify.service;

import com.google.common.collect.Lists;
import com.puffer.core.log.Log;
import com.puffer.shopify.common.enums.ColorEnum;
import com.puffer.shopify.common.enums.ForTypeEnum;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.common.enums.ProductStateEnum;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.entity.ProductExtDO;
import com.puffer.shopify.entity.ProductImageDO;
import com.puffer.shopify.entity.ProductRankDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.mapper.ProductExtDao;
import com.puffer.shopify.mapper.ProductImageDao;
import com.puffer.shopify.mapper.ProductRankDao;
import com.puffer.shopify.mapper.ProductVariantDao;
import com.puffer.shopify.service.amazon.AmazonService;
import com.puffer.shopify.service.shopify.ShopifyProductService;
import com.puffer.shopify.vo.ProductExcelVO;
import com.puffer.shopify.vo.ProductVO;
import com.puffer.shopify.vo.shopify.ShopifyProduct;
import com.puffer.util.lang.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    private ProductExtDao productExtDao;

    @Resource
    private ProductRankDao productRankDao;

    @Resource
    private ProductImageDao productImageDao;

    @Resource
    private ProductVariantDao productVariantDao;

    @Resource
    private ShopifyProductService shopifyProductService;

    @Resource
    private ThreadLocalService threadLocalService;

    @Resource
    private AmazonService amazonService;

    @Resource
    private KeywordsService keywordsService;

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

            //step5.保存产品扩展信息
            productExtDao.insert(productVO.getProductExtDO());

        } catch (Exception e) {
            log.error(Log.newInstance("", "保存数据异常").kv("spu", productVO.getProductDO().getSpu()).kv("url", productVO.getProductDO().getUrl()).toString(), e);
            throw e;
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

        ProductExtDO productExtDO = productExtDao.query(productDO.getSpu());

        return ProductVO.builder().productDO(productDO).productImageDOList(productImageDOS).productExtDO(productExtDO).build();
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
            productDO.setFlowState(ProductFlowStateEnum.UPDATE_FROM_SHOPIFY.getValue());
        }

        productDao.updateTitle(productDO);
    }

    public void collectProduct(String filePath) throws IOException {

        Map<String, ProductExcelVO> productExcelVOMap = threadLocalService.queryProductExcel();
        //step1. 读取excel信息
        List<Object[]> objects = ExcelUtil.readFile(filePath, 1);
        List<String> urls = Lists.newArrayList();

        for (Object[] object : objects) {
            if (object[0] == null) {
                continue;
            }

            ProductExcelVO productExcelVO = new ProductExcelVO();
            productExcelVO.setUrl(String.valueOf(object[0]).trim());
            productExcelVO.setSource(String.valueOf(object[1]).trim());
            productExcelVO.setColor(ColorEnum.fromValue(String.valueOf(object[2]).trim()));
            productExcelVO.setForType(ForTypeEnum.from(String.valueOf(object[3]).trim()));
            productExcelVO.setQuotes(String.valueOf(object[4]));
            productExcelVO.setCapacity(Double.valueOf(object[5].toString()).intValue());
            productExcelVO.setSafeLevel(Double.valueOf(object[6].toString()).intValue());

            if (object.length >= 8 && StringUtils.isNotBlank(object[7].toString())) {
                productExcelVO.setPrice(new BigDecimal(object[7].toString()));
            }
            // productExcelVO.setPrice(new BigDecimal());
            //            productExcelVO.setTitle();
            //            productExcelVO.setDescrption();
            //            productExcelVO.setPrice();
            //            productExcelVO.setKeywords();
            //            productExcelVO.setNewTitle();
            //            productExcelVO.setNewDescription();
            //            productExcelVO.setNewPrice();
            //            productExcelVO.setImageUrl();
            urls.add(productExcelVO.getUrl());
            productExcelVOMap.put(productExcelVO.getUrl(), productExcelVO);
        }

        //step.2 触发爬虫
        amazonService.process(urls);

        //step3.生成新的excel
        createExcel(ProductFlowStateEnum.TO_UPDATE_TITLE, filePath);

        // List<String[]> lines = Lists.newArrayList();
        // lines.add(ProductExcelVO.createTitle());
        // productExcelVOMap.values().forEach(productExcelVO -> {
        //     lines.add(productExcelVO.transfer());
        // });
        //
        // ExcelUtil.createFile(filePath, lines);

        //step3. 匹配关键词

        //step4. 生成新的标题
        //step5. 生成新的描述
        //step6. 保存产品信息到数据库
        //step7. 上传产品到store
        //step8. 发布产品
    }

    public void createExcel(ProductFlowStateEnum flowStateEnum, String filePath) throws IOException {
        List<ProductVO> productVOS = queryList(flowStateEnum, 100);

        List<String[]> lines = Lists.newArrayList();
        lines.add(ProductExcelVO.createTitle());

        for (ProductVO productVO : productVOS) {
            ProductDO productDO = productVO.getProductDO();
            ProductExtDO productExtDO = productVO.getProductExtDO();
            ProductImageDO productImageDO = productVO.getProductImageDOList().get(0);

            ProductExcelVO productExcelVO = new ProductExcelVO();
            productExcelVO.setUrl(productDO.getUrl());
            productExcelVO.setSource("amazon");
            productExcelVO.setColor(ColorEnum.fromValue(productExtDO.getColor()));
            productExcelVO.setForType(ForTypeEnum.from(productExtDO.getForType()));
            productExcelVO.setQuotes(productExtDO.getQuotes());
            productExcelVO.setCapacity(productExtDO.getCapacity());
            productExcelVO.setSafeLevel(productExtDO.getSafeLevel());
            productExcelVO.setPrice(productDO.getAmazonPrice());
            productExcelVO.setSpu(productDO.getSpu());
            productExcelVO.setTitle(productDO.getTitle());
            productExcelVO.setDescription(productDO.getDescription());
            // productExcelVO.setKeywords("");
            productExcelVO.setNewTitle("");
            productExcelVO.setNewDescription("");
            productExcelVO.setNewPrice(productDO.getShopifyPrice());
            productExcelVO.setImageUrl(productImageDO.getImageUrl());

            //查询关键词
            String keyword = keywordsService.queryKeyword(productExcelVO.getForType());
            productExcelVO.setKeywords(keyword);

            lines.add(productExcelVO.transfer());
        }

        ExcelUtil.createFile(filePath, lines);

    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }

}
