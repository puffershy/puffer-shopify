package com.puffer.shopify.service.amazon;

import com.puffer.shopify.common.constants.AmazonConstant;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.service.ProductService;
import com.puffer.shopify.spider.pipeline.AmazonExcelPipeline;
import com.puffer.shopify.spider.pipeline.AmazonPipeline;
import com.puffer.shopify.spider.pipeline.AmazonUpdatePipeline;
import com.puffer.shopify.spider.processor.AmazonPageProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;

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

    @Resource
    private AmazonExcelPipeline amazonExcelPipeline;


    /**
     * 爬虫并生成excel
     *
     * @param urls
     * @author puffer
     * @date 2020年07月11日 09:29:38
     * @since 1.0.0
     */
    public void processExcel(List<String> urls) {
        instanceSpirder(amazonExcelPipeline, urls);
    }


    /**
     * 爬取amazon产品信息
     *
     * @param url
     * @return void
     * @author puffer
     * @date 2020年07月11日 09:17:12
     * @since 1.0.0
     */
    public void process(String url) {
//        Spider.create(amazonPageProcessor)
//                .addUrl(url)
//                .addPipeline(amazonPipeline)
//                //开启5个线程抓取
//                .thread(AmazonConstant.SPIDER_THREAD_NUM)
//                //启动爬虫
//                .run();
        instanceSpirder(amazonPipeline, Lists.newArrayList(url)).run();
    }

//    public void updateImageList(List<ProductDO> productDOList) {
//        List<String> urlList = productDOList.stream().map(ProductDO::getUrl).collect(Collectors.toList());
//
//        updateImage(urlList);
//    }

    /**
     * 更新amazon 图片
     *
     * @param urlList
     * @return void
     * @author puffer
     * @date 2020年07月11日 09:18:43
     * @since 1.0.0
     */
    public void updateImage(List<String> urlList) {

        Spider spider = instanceSpirder(amazonUpdatePipeline, urlList);
        spider.run();
//        Spider.create(amazonPageProcessor)
//                .addUrl(urlList.toArray(new String[urlList.size()]))
//                .addPipeline(amazonUpdatePipeline)
//                //开启5个线程抓取
//                .thread(AmazonConstant.SPIDER_THREAD_NUM)
//                //启动爬虫
//                .run();
    }


    /**
     * 初始化爬虫
     *
     * @param pipeline
     * @param urls
     * @return us.codecraft.webmagic.Spider
     * @author puffer
     * @date 2020年07月11日 09:23:36
     * @since 1.0.0
     */

    private Spider instanceSpirder(Pipeline pipeline, List<String> urls) {
        Spider spider = Spider.create(amazonPageProcessor);
        spider.addPipeline(pipeline).thread(AmazonConstant.SPIDER_THREAD_NUM);

        if (!CollectionUtils.isEmpty(urls)) {
            //如果地址不为空，则添加地址
            spider.addUrl(urls.toArray(new String[urls.size()]));
        }
        return spider;

    }

}
