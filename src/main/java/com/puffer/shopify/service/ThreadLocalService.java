package com.puffer.shopify.service;

import com.google.common.collect.Maps;
import com.puffer.shopify.vo.ProductExcelVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ThreadLocalService {
    // private ThreadLocal<Map<String, ProductExcelVO>>  productExcelThreadLocal = new ThreadLocal<>();
    private Map<String, ProductExcelVO> productExcelThreadLocal = Maps.newConcurrentMap();

    public Map<String, ProductExcelVO> queryProductExcel() {
        // Map<String, ProductExcelVO> stringProductExcelVOMap = productExcelThreadLocal.get();
        // if (stringProductExcelVOMap == null) {
        //     stringProductExcelVOMap = Maps.newConcurrentMap();
        //     productExcelThreadLocal.set(stringProductExcelVOMap);
        // }
        //
        // return stringProductExcelVOMap;
        return productExcelThreadLocal;
    }

    public ProductExcelVO queryProductExcel(String url) {
        return productExcelThreadLocal.get(url);
    }

}