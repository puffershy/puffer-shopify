package com.puffer.shopify.spider.processor;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.spider.pipeline.AmazonPipeline;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;
import java.util.List;

import static com.puffer.shopify.common.constants.PatternConstants.AMAZON_BEST_SELLER_URL;

public class AmazonPageProcessorTest extends AbstractTest {


    @Resource
    private AmazonPipeline amazonPipeline;

    @Test
    public void testProcess() {
//         String url = "https://www.amazon.com/Best-Sellers-Kitchen-Dining-Novelty-Coffee-Mugs/zgbs/kitchen/9302388011/ref=zg_bs_pg_1?_encoding=UTF8&pg=1";
//        String url = "https://www.amazon.com/Anti-Social-Quarantine-Distancing-COVID-19-2020-collecting/dp/B087QNFK9K/ref=zg_bs_9302388011_1?_encoding=UTF8&refRID=7RRWH3NSNCAEP4CVX23W&th=1";
        // String url = "https://tech.163.com/20/0522/10/FD7P0UIE00097U7R.html";

        String url = "https://www.amazon.com/gp/new-releases/kitchen/9302388011/ref=zg_bsnr_pg_1?ie=UTF8&pg=1";
        Spider.create(new AmazonPageProcessor())
                .addUrl(url)
                .addPipeline(amazonPipeline)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }


    @Test
    public void testUrl(){
        PlainText plainText = PlainText.create("https://www.amazon.com/gp/new-releases/kitchen/9302388011/ref=zg_bsnr_pg_2?ie=UTF8&pg=2");

        Selectable regex = plainText.regex(".*Best-Sellers.* | .*new-releases.*");
        System.out.println(regex.match());
    }

    public static void main(String[] args) {

        List<String> list = Lists.newArrayList();

        list.add("https://www.amazon.com/gp/new-releases/kitchen/9302388011/ref=zg_bsnr_pg_2?ie=UTF8&pg=2");
        list.add("https://www.amazon.com/gp/Best-Sellers/kitchen/9302388011/ref=zg_bsnr_pg_2?ie=UTF8&pg=2");
        list.add("https://www.amazon.com/Vilight-Nurse-Practitioner-Graduation-Gifts/dp/B0857J8M1B/ref=zg_bsnr_9302388011_51?_encoding=UTF8&psc=1&refRID=7PZA5FSRVANPKKXE6YD4");


        for (String s : list) {
            PlainText plainText = PlainText.create(s);
            System.out.println();

        Selectable regex = plainText.regex(AMAZON_BEST_SELLER_URL);
        System.out.println(regex.match());
        }

    }
}