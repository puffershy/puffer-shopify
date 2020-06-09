package com.puffer.shopify.spider.processor;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.io.IOException;

// public class ShopifyProcessorTest extends AbstractTest {
public class ShopifyProcessorTest {

    // @Resource
    // private ShopifyProcessor shopifyProcessor;

    @Test
    public void test() {
        String url = "https://gojeek.com/ar/products/unique-just-do-it-painting-nike-shower-curtain?variant=29458073911338";
        //  String url = "https://www.amazon.com/Coffee-Remember-Graduation-Quarantine-Ceramic/dp/B08742Y2XN/ref=nav_signin?_encoding=UTF8&psc=1&refRID=6WT78YTWCGCQSHWMF94E&";
        Spider.create(new ShopifyProcessor())
                .addUrl(url)
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

    }

    @Test
    public void test1() throws IOException {
        String url = "https://gojeek.com/ar/products/unique-just-do-it-painting-nike-shower-curtain?variant=29458073911338";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get().build();
        Response execute = client.newCall(request).execute();
        System.out.println(execute.body().string());

        // return client.newCall(request).execute();
    }

}