package com.puffer.shopify.service;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.common.enums.ProductStateEnum;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.vo.ProductVO;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.testng.Assert.*;

public class ProductServiceTest extends AbstractTest {
    @Resource
    private ProductService productService;
    @Resource
    private ProductDao productDao;

    @Test
    public void testUpdateFromShopify() {

        int size = 300;


        List<ProductDO> productDOS = productDao.queryList(ProductFlowStateEnum.UPLOAD_SUCCESS.getValue(), size, ProductStateEnum.EFFECTIVE.getValue());
        for (ProductDO productDO : productDOS) {
            productService.updateFromShopify(productDO);
        }
    }
}