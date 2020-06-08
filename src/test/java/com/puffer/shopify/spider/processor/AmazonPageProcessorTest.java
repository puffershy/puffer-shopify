package com.puffer.shopify.spider.processor;

import com.puffer.shopify.AbstractTest;
import com.puffer.shopify.common.enums.ProductFlowStateEnum;
import com.puffer.shopify.entity.ProductDO;
import com.puffer.shopify.service.AmazonService;
import com.puffer.shopify.service.ProductService;
import com.puffer.shopify.spider.pipeline.AmazonPipeline;
import com.puffer.shopify.vo.ProductVO;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.puffer.shopify.common.constants.PatternConstants.AMAZON_BEST_SELLER_URL;

public class AmazonPageProcessorTest extends AbstractTest {


    @Resource
    private AmazonPageProcessor amazonPageProcessor;

    @Resource
    private AmazonPipeline amazonPipeline;

    @Resource
    private ProductService productService;

    @Resource
    private AmazonService amazonService;

    @Test
    public void testProcess() {

//       String url = "https://www.amazon.com/gp/new-releases/kitchen/9302388011/ref=zg_bsnr_pg_1?ie=UTF8&pg=1";
         String url = "https://www.amazon.com/dp/B07NPFDCC5/ref=sspa_dk_detail_7?psc=1&pd_rd_i=B07NPFDCC5&pd_rd_w=9dEgl&pf_rd_p=a64be657-55f3-4b6a-91aa-17a31a8febb4&pd_rd_wg=C1y1h&pf_rd_r=23WE1AKWQN2V4XNRC1W1&pd_rd_r=f5de8ee2-1907-4107-bab4-116529d2fa91&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEyUTZGS0c4TkU3TUZWJmVuY3J5cHRlZElkPUEwMDU2Njk3SzVZNUs5WERSRUdCJmVuY3J5cHRlZEFkSWQ9QTAyNjAxNTBaUzFNV1BOWjNJTkMmd2lkZ2V0TmFtZT1zcF9kZXRhaWxfdGhlbWF0aWMmYWN0aW9uPWNsaWNrUmVkaXJlY3QmZG9Ob3RMb2dDbGljaz10cnVl";
        Spider.create(amazonPageProcessor)
                .addUrl(url)
                .addPipeline(amazonPipeline)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

    @Test
    public void readText() throws IOException {
        List<String> strings = FileUtils.readLines(new File("/Users/yuchanghui/Documents/doswarm/amazonlinks"));

        Spider.create(amazonPageProcessor)
                .addUrl(strings.toArray(new String[strings.size()]))
                .addPipeline(amazonPipeline)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

    }





    @Test
    public void testProcess2() {

        System.setProperty("selenuim_config",Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/config.ini");
        String chromePath =  System.getProperty("user.dir").concat("/chromedriver.exe");

        //        String url = "https://www.amazon.com/gp/new-releases/kitchen/9302388011/ref=zg_bsnr_pg_1?ie=UTF8&pg=1";
        String url = "https://www.amazon.com/Coffee-Remember-Graduation-Quarantine-Ceramic/dp/B08742Y2XN/ref=nav_signin?_encoding=UTF8&psc=1&refRID=6WT78YTWCGCQSHWMF94E&";
        Spider.create(new AmazonPageProcessor())
                .addUrl(url)
                // .addPipeline(amazonPipeline)
                .addPipeline( new ConsolePipeline())
                //开启5个线程抓取
                .thread(5).setDownloader(new SeleniumDownloader(chromePath))
                //启动爬虫
                .run();
    }


    @Test
    public void updateImage() {

        List<ProductVO> productVOS = productService.queryList(ProductFlowStateEnum.TO_UPLOAD, 150);

        List<String> urlList = productVOS.stream().map(ProductVO::getProductDO).map(ProductDO::getUrl).collect(Collectors.toList());

        amazonService.updateImage(urlList);

    }


    @Test
    public void testUrl() {
        PlainText plainText = PlainText.create("https://www.amazon.com/gp/new-releases/kitchen/9302388011/ref=zg_bsnr_pg_2?ie=UTF8&pg=2");

        Selectable regex = plainText.regex(".*Best-Sellers.* | .*new-releases.*");
        System.out.println(regex.match());
    }

    public static void main(String[] args) {

        // List<String> list = Lists.newArrayList();
        //
        // list.add("https://www.amazon.com/gp/new-releases/kitchen/9302388011/ref=zg_bsnr_pg_2?ie=UTF8&pg=2");
        // list.add("https://www.amazon.com/gp/Best-Sellers/kitchen/9302388011/ref=zg_bsnr_pg_2?ie=UTF8&pg=2");
        // list.add("https://www.amazon.com/Vilight-Nurse-Practitioner-Graduation-Gifts/dp/B0857J8M1B/ref=zg_bsnr_9302388011_51?_encoding=UTF8&psc=1&refRID=7PZA5FSRVANPKKXE6YD4");
        //
        //
        // for (String s : list) {
        //     PlainText plainText = PlainText.create(s);
        //     System.out.println();
        //
        //     Selectable regex = plainText.regex(AMAZON_BEST_SELLER_URL);
        //     System.out.println(regex.match());
        // }

        System.out.println( System.getProperty("user.dir"));
    }
}