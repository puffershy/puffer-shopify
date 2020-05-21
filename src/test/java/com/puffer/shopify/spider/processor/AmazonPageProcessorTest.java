package com.puffer.shopify.spider.processor;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.spider.pipeline.AmazonPipeline;
import org.testng.annotations.Test;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

public class AmazonPageProcessorTest extends AbstractTest {


    @Resource
    private AmazonPipeline amazonPipeline;

    @Test
    public void testProcess() {
        String url = "https://www.amazon.com/Best-Sellers-Kitchen-Dining-Novelty-Coffee-Mugs/zgbs/kitchen/9302388011/ref=zg_bs_pg_1?_encoding=UTF8&pg=1";
        Spider.create(new AmazonPageProcessor())
                .addUrl(url)
                .addPipeline(amazonPipeline)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}