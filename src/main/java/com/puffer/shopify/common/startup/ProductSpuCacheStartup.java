package com.puffer.shopify.common.startup;

import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.mapper.ProductDao;
import com.puffer.shopify.spider.processor.AmazonPageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author puffer
 * @date 2020年05月28日 19:56:13
 * @since
 */
@Slf4j
@Component
public class ProductSpuCacheStartup implements CommandLineRunner {

    @Resource
    private AmazonPageProcessor amazonPageProcessor;

    @Resource
    private ProductDao productDao;
    @Override
    public void run(String... args) throws Exception {

        List<ProductDO> productDOList= productDao.queryAll();

        List<String> collect = productDOList.stream().map(ProductDO::getSpu).collect(Collectors.toList());

        amazonPageProcessor.setSpuList(collect);

    }
}
