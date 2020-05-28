package com.puffer.shopify.service;

import com.puffer.shopify.common.constants.AmazonConstant;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.spider.pipeline.AmazonPipeline;
import com.puffer.shopify.spider.pipeline.AmazonUpdatePipeline;
import com.puffer.shopify.spider.processor.AmazonPageProcessor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmazonService {

    @Resource
    private AmazonPipeline amazonPipeline;

    @Resource
    private AmazonUpdatePipeline amazonUpdatePipeline;

    @Resource
    private ProductService productService;

    @Resource
    private AmazonPageProcessor amazonPageProcessor;

    public void process(String url) {
        Spider.create(amazonPageProcessor)
                .addUrl(url)
                .addPipeline(amazonPipeline)
                //开启5个线程抓取
                .thread(AmazonConstant.SPIDER_THREAD_NUM)
                //启动爬虫
                .run();
    }

    public void updateImageList(List<ProductDO> productDOList) {
        List<String> urlList = productDOList.stream().map(ProductDO::getUrl).collect(Collectors.toList());

        updateImage(urlList);
    }

    public void updateImage(List<String> urlList) {
        Spider.create(amazonPageProcessor)
                .addUrl(urlList.toArray(new String[urlList.size()]))
                .addPipeline(amazonUpdatePipeline)
                //开启5个线程抓取
                .thread(AmazonConstant.SPIDER_THREAD_NUM)
                //启动爬虫
                .run();
    }

}
